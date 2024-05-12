package factorio.calculator;

import factorio.object.*;
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

    public FactorioCalculator(){

    }
    public Blueprint CalculateBluePrintWithFile(int height, int width, double brightness,int substationOffsetX, int substationOffsetY,  boolean substations, boolean optimizeSignals, List<File> pictureList) throws FileNotFoundException{
        var FilesAsInputStreams = new LinkedList<InputStream>();
        for (File x : pictureList){
            FilesAsInputStreams.add(new FileInputStream(x));
        }
        return CalculateBluePrint(height, width, brightness, substationOffsetX, substationOffsetY, substations, optimizeSignals, FilesAsInputStreams);
    }

    public Blueprint CalculateBluePrint(int height, int width, double brightness,int substationOffsetX, int substationOffsetY,  boolean substations, boolean optimizeSignals, List<InputStream> pictureList){
        //calculate raw values from pixels without optimisation
        Integer[][] arrangement = calculateRawValues(height,width, brightness, pictureList);
        //start placing the blueprint entities
        Entity.setEntityCount(0);
        ArrayList<Entity> entities = new ArrayList<>();
        //add substations and remove lights
        if (substations) entities.addAll(calculateSubstations(arrangement,height,width,substationOffsetX,substationOffsetY));
        //optimize values into output
        ArrayList<Integer> signalValues = new ArrayList<>();
        int lastLamp = 0;
        boolean placedCombinators = false, redLine = false;
        for (int column = 0; column < width; column++) {
            //check ahead for new signals
            int valuesLength = signalValues.size();
            if (column > 0) {
                for (int row = 0; row < height; row++) {
                    if (arrangement[row][column] == null) {
                        continue;
                    }
                    if ((!signalValues.contains(arrangement[row][column]) && arrangement[row][column] != 0) || !optimizeSignals) {
                        signalValues.add(arrangement[row][column]);
                    }
                }
            }
            //place combinators
            if (!SignalID.hasID(signalValues.size() - 1)) {
                signalValues.subList(valuesLength, signalValues.size()).clear();
                entities.addAll(calculateCombinators(signalValues, column, width));
                signalValues.clear();
                placedCombinators = true;
                redLine = true;
            }
            for (int row = 0; row < height; row++) {
                if (arrangement[row][column] == null) {
                    continue;
                }
                Entity lamp = new Entity(
                        "small-lamp",
                        new Position(-width + column + .5F, -height + row + .5F)
                );
                // behaviour
                if (signalValues.contains(arrangement[row][column]) && optimizeSignals) {
                    lamp.setControlBehavior(new ControlBehaviour(true, new CircuitCondition(
                            SignalID.getID(signalValues.indexOf(arrangement[row][column]))
                    )));
                } else if (arrangement[row][column] != 0 || !optimizeSignals) {
                    signalValues.add(arrangement[row][column]);
                    lamp.setControlBehavior(new ControlBehaviour(true, new CircuitCondition(
                            SignalID.getID(signalValues.size() - 1)
                    )));
                }
                // connections
                ArrayList<ConnectionData> GreenConnections = new ArrayList<>();
                ArrayList<ConnectionData> RedConnections = new ArrayList<>();
                if (row != 0) GreenConnections.add(new ConnectionData(lamp.entity_number - 1));
                if (row == height - 1) {
                    if (column != 0) {
                        if (!placedCombinators) GreenConnections.add(new ConnectionData(lastLamp));
                        if (redLine) RedConnections.add(new ConnectionData(lastLamp));
                    }
                    lastLamp = lamp.entity_number;
                }
                lamp.setConnections(new Connection(new ConnectionPoint(RedConnections, GreenConnections)));
                entities.add(lamp);
            }
            placedCombinators = false;
        }
        final int finalLastLamp = lastLamp;
        if (signalValues.size() > 0) entities.addAll(calculateCombinators(signalValues, width, width));//Second width was global access before refactor LOL
        //frame control combinator
        entities.add(new Entity(
                "constant-combinator",
                new Position(.5F, .5F),
                0,
                new ControlBehaviour(new ArrayList<>() {{
                    add(new Filter(new SignalID("signal-black"), 0, 1));
                    add(new Filter(new SignalID("signal-white"), 1, 11));
                }}),
                new Connection(new ConnectionPoint(new ArrayList<ConnectionData>() {{
                    add(new ConnectionData(finalLastLamp));
                }}, null))
        ));
        //blueprint
        return new Blueprint(
                "FactorioDMM-output",
                entities,
                new Icon[]{new Icon(1, new SignalID("small-lamp"))},
                281479271743489L
        );
    }

    private Integer[][] calculateRawValues(int height, int width, double brightness, List<InputStream> pictureList) {
        Integer[][] arrangement = new Integer[height][width];
        Arrays.stream(arrangement).forEach(a -> Arrays.fill(a, 0));
        if (pictureList != null) {
            for (int imageCount = 0; imageCount < pictureList.size() && imageCount < 32; imageCount++) {
                Image image = new Image(pictureList.get(imageCount));
                PixelReader pixelReader = image.getPixelReader();
                int imageWidth = (int) image.getWidth(), imageHeight = (int) image.getHeight();
                for (int readY = 0; readY < height && readY < imageHeight; readY++) {
                    for (int readX = 0; readX < width && readX < imageWidth; readX++) {
                        if (pixelReader.getColor(readX, readY).getBrightness() >= brightness) {
                            arrangement[readY][readX] = setBit(imageCount, arrangement[readY][readX]);
                        }
                    }
                }
            }
        }
        return arrangement;
    }

    private ArrayList<Entity> calculateSubstations(Integer[][] arrangement,int height, int width,int substationOffsetX, int substationOffsetY) {
        return new ArrayList<Entity>() {{
            for (int subX = substationOffsetX + width; subX > -9; subX -= 18) {
                for (int subY = substationOffsetY + height; subY > -9; subY -= 18) {
                    Entity substation = new Entity(
                            "substation",
                            new Position(-width + subX * 1F, -height + subY * 1F)
                    );
                    // neighbouring substations
                    ArrayList<Integer> neighbours = new ArrayList<>();
                    if (subY > 9) neighbours.add(Entity.getEntityCount() + 1);
                    if (subY != substationOffsetY + height) neighbours.add(Entity.getEntityCount() - 1);
                    else {
                        if (subX > 9)
                            neighbours.add((int) (Entity.getEntityCount() + Math.ceil((substationOffsetY + height + 9) / 18.0)));
                        if (subX != substationOffsetX + width)
                            neighbours.add((int) (Entity.getEntityCount() - Math.ceil((substationOffsetX + width + 9) / 18.0)));
                    }
                    substation.setNeighbours(neighbours);
                    add(substation);
                    //remove lights
                    if (subX <= width && subY <= height) {
                        if (subX < width) {
                            if (subX >= 0 && subY >= 0) {
                                arrangement[subY][subX] = null;
                                if (subY - 1 >= 0) arrangement[subY - 1][subX] = null;
                            }
                        }
                        if (subX - 1 >= 0 && subY >= 0) {
                            arrangement[subY][subX - 1] = null;
                            if (subY - 1 >= 0) arrangement[subY - 1][subX - 1] = null;
                        }
                    }
                }
            }
        }};
    }

    private ArrayList<Entity> calculateCombinators(ArrayList<Integer> signalValues, int column, int width) {
        float widthOffset = column - width;
        return new ArrayList<>() {{
            add(new Entity(
                    "arithmetic-combinator",
                    new Position(widthOffset - .5F, 1F),
                    0,
                    new ControlBehaviour(new ArithmeticCondition(
                            new SignalID("signal-each"),
                            new SignalID("signal-black"),
                            "<<",
                            new SignalID("signal-each")
                    )),
                    new Connection(
                            new ConnectionPoint(new ArrayList<>() {{
                                add(new ConnectionData(Entity.getEntityCount()));
                            }}, new ArrayList<>() {{
                                add(new ConnectionData(Entity.getEntityCount() + 2));
                            }}),
                            new ConnectionPoint(null, new ArrayList<>() {{
                                add(new ConnectionData(Entity.getEntityCount()));
                            }})
                    )
            ));
            for (int combinator = 0; combinator <= (signalValues.size() - 1) / 20; combinator++) {
                int combinatorOffset = combinator * 20;
                ArrayList<Filter> filters = new ArrayList<>();
                for (int signal = 0; signal < 20 && combinatorOffset + signal < signalValues.size(); signal++) {
                    int signalValue = combinatorOffset + signal;
                    if (SignalID.hasID(signalValue)) filters.add(
                            new Filter(SignalID.getID(signalValue), signalValues.get(signalValue), signal + 1)
                    );
                }
                if (filters.size() > 0) {
                    add(new Entity(
                            "constant-combinator",
                            new Position(widthOffset - .5F, combinator + 2.5F),
                            0,
                            new ControlBehaviour(filters),
                            new Connection(new ConnectionPoint(null, new ArrayList<>() {{
                                add(new ConnectionData(Entity.getEntityCount()));
                            }}))
                    ));
                }
            }
        }};
    }

    private int setBit(int bit, int target) {
        int mask = 1 << (31 - bit);
        return target | mask;
    }
}
