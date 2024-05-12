package factorio.calculator;

import factorio.factory.EntityBuilder;
import factorio.factory.SignalLibrary;
import factorio.factory.entities.ArithmeticCombinator;
import factorio.factory.entities.ConstantCombinator;
import factorio.factory.entities.SmallLamp;
import factorio.object.Blueprint;
import factorio.object.Entity;
import factorio.object.Operation;
import factorio.object.Signal;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FactorioCalculator {

    private Integer[][] arrangement;

    public FactorioCalculator() {

    }

    public Blueprint CalculateBluePrintWithFile(int height, int width, double brightness, int substationOffsetX, int substationOffsetY, boolean substations, boolean optimizeSignals, List<File> pictureList) throws FileNotFoundException {
        var FilesAsInputStreams = new LinkedList<InputStream>();
        for (File x : pictureList) {
            FilesAsInputStreams.add(new FileInputStream(x));
        }
        return CalculateBluePrint(height, width, brightness, substationOffsetX, substationOffsetY, substations, optimizeSignals, FilesAsInputStreams);
    }

    public Blueprint CalculateBluePrint(int height, int width, double brightness, int substationOffsetX, int substationOffsetY, boolean substations, boolean optimizeSignals, List<InputStream> pictureList) {
        //calculate raw values from pixels without optimisation
        calculateRawValues(height, width, brightness, pictureList);
        //start placing the blueprint entities
        ArrayList<Entity> entities = new ArrayList<>();
        Entity.resetEntityCount();
        //add substations and remove lights
        if (substations) entities.addAll(calculateSubstations(height, width, substationOffsetX, substationOffsetY));
        //optimize values into output
        ArrayList<Integer> signalValues = new ArrayList<>();
        int lastLamp = 0;
        boolean placedCombinators = false, redLine = false;
        for (int column = 0; column < width; column++) {
            //check ahead for new signals
            int valuesLength = signalValues.size();
            if (column > 0) {
                for (int row = 0; row < height; row++) {
                    if (arrangement[row][column] == null) continue;
                    if ((!signalValues.contains(arrangement[row][column])  arrangement[row][column] != 0) || !optimizeSignals)
                        signalValues.add(arrangement[row][column]);
                }
            }
            //place combinators
            if (!SignalLibrary.has(signalValues.size() - 1)) {
                signalValues.subList(valuesLength, signalValues.size()).clear();
                entities.addAll(calculateCombinators(signalValues, column, width));
                signalValues.clear();
                placedCombinators = true;
                redLine = true;
            }
            //place lamps
            for (int row = 0; row < height; row++) {
                if (arrangement[row][column] == null) continue;
                SmallLamp lamp = new SmallLamp(-width + column + .5F, -height + row + .5F);
                // behaviour
                if (signalValues.contains(arrangement[row][column]) && optimizeSignals) {
                    lamp.setCondition(true, SignalLibrary.get(signalValues.indexOf(arrangement[row][column])));
                } else if (arrangement[row][column] != 0 || !optimizeSignals) {
                    signalValues.add(arrangement[row][column]);
                    lamp.setCondition(true, SignalLibrary.get(signalValues.size() - 1));
                }
                // connections
                if (row != 0) lamp.addGreenConnection(lamp.previous_number);
                if (row == height - 1) {
                    if (column != 0) {
                        if (!placedCombinators) lamp.addGreenConnection(lastLamp);
                        if (redLine) lamp.addRedConnection(lastLamp);
                    }
                    lastLamp = lamp.entity_number;
                }
                entities.add(lamp);
            }
            placedCombinators = false;
        }
        if (!signalValues.isEmpty())
            entities.addAll(calculateCombinators(signalValues, width, width)); //Second width was global access before refactor LOL
        //frame control combinator
        entities.add(new ConstantCombinator(.5F, .5F).addFilter(Signal.BLACK, 0, 1).addFilter(Signal.WHITE, 1, 11).addRedConnection(lastLamp));
        //blueprint
        return new Blueprint("FactorioDMM-output", entities, SignalLibrary.getIcons(Signal.SMALL_LAMP), 281479271743489L);
    }

    private void calculateRawValues(int height, int width, double brightness, List<InputStream> pictureList) {
        arrangement = new Integer[height][width];
        Arrays.stream(arrangement).forEach(a -> Arrays.fill(a, 0));
        if (pictureList != null)
            for (int imageCount = 0; imageCount < pictureList.size() && imageCount < 32; imageCount++) {
                Image image = new Image(pictureList.get(imageCount));
                PixelReader pixelReader = image.getPixelReader();
                int imageWidth = (int) image.getWidth(), imageHeight = (int) image.getHeight();
                for (int readY = 0; readY < height && readY < imageHeight; readY++)
                    for (int readX = 0; readX < width && readX < imageWidth; readX++)
                        if (pixelReader.getColor(readX, readY).getBrightness() >= brightness)
                            arrangement[readY][readX] = setBit(imageCount, arrangement[readY][readX]);
            }
    }

    private ArrayList<Entity> calculateSubstations(int height, int width, int substationOffsetX, int substationOffsetY) {
        return new ArrayList<>() {{
            for (int subX = substationOffsetX + width; subX > -9; subX -= 18)
                for (int subY = substationOffsetY + height; subY > -9; subY -= 18) {
                    Entity substation = buildSubstation(subX, subY, width, height, substationOffsetX, substationOffsetY);
                    add(substation);
                    //remove lights
                    if (subX <= width && subY <= height) {
                        if (subX < width) if (subX >= 0 && subY >= 0) {
                            arrangement[subY][subX] = null;
                            if (subY - 1 >= 0) arrangement[subY - 1][subX] = null;
                        }
                        if (subX - 1 >= 0 && subY >= 0) {
                            arrangement[subY][subX - 1] = null;
                            if (subY - 1 >= 0) arrangement[subY - 1][subX - 1] = null;
                        }
                    }
                }
        }};
    }

    private Entity buildSubstation(int subX, int subY, int width, int height, int substationOffsetX, int substationOffsetY) {
        EntityBuilder substation = new EntityBuilder("substation", -width + subX * 1F, -height + subY * 1F);
        if (subY > 9) substation.addNeighbour(substation.next_number);
        if (subY != substationOffsetY + height) substation.addNeighbour(substation.previous_number);
        else {
            if (subX > 9)
                substation.addNeighbour(substation.entity_number + (int) Math.ceil((substationOffsetY + height + 9) / 18.0));
            if (subX != substationOffsetX + width)
                substation.addNeighbour(substation.entity_number - (int) Math.ceil((substationOffsetX + width + 9) / 18.0));
        }
        return substation.build();
    }

    private ArrayList<Entity> calculateCombinators(ArrayList<Integer> signalValues, int column, int width) {
        float widthOffset = column - width;
        return new ArrayList<>() {{
            ArithmeticCombinator arithmeticCombinator = new ArithmeticCombinator(widthOffset - .5F, 1F);
            add(arithmeticCombinator.setCondition(Signal.EACH, Signal.BLACK, Operation.LEFT_SHIFT, Signal.EACH).addRedInputConnection(arithmeticCombinator.previous_number).addGreenInputConnection(arithmeticCombinator.next_number).addGreenOutputConnection(arithmeticCombinator.previous_number));
            for (int combinator = 0; combinator <= (signalValues.size() - 1) / ConstantCombinator.maxSignals; combinator++) {
                int combinatorOffset = combinator * ConstantCombinator.maxSignals;
                if (!SignalLibrary.has(combinatorOffset)) continue;
                ConstantCombinator constantCombinator = new ConstantCombinator(widthOffset - .5F, combinator + 2.5F);
                for (int signal = 0; signal < ConstantCombinator.maxSignals && combinatorOffset + signal < signalValues.size(); signal++) {
                    int signalValue = combinatorOffset + signal;
                    if (SignalLibrary.has(signalValue))
                        constantCombinator.addFilter(SignalLibrary.get(signalValue), signalValues.get(signalValue), signal + 1);
                }
                add(constantCombinator.addGreenConnection(constantCombinator.previous_number));
            }
        }};
    }

    private int setBit(int bit, int target) {
        int mask = 1 << (31 - bit);
        return target | mask;
    }
}
