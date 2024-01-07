package factorio.gui;

import factorio.decoders.GifDecoder.DecodedGif;
import factorio.decoders.GifDecoder.GifDecoder;
import factorio.encoders.BlueprintStringEncoder;
import factorio.factory.EntityBuilder;
import factorio.factory.SignalLibrary;
import factorio.factory.entities.ArithmeticCombinator;
import factorio.factory.entities.ConstantCombinator;
import factorio.factory.entities.SmallLamp;
import factorio.object.Blueprint;
import factorio.object.Entity;
import factorio.object.Operation;
import factorio.object.Signal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.ScrollEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BitGIFGeneratorController {

    private ObservableList<File> pictureList;
    private int width, height, fontSize, previousIndex = 0;
    private double brightness;
    private boolean copyMode = false;
    private Integer[][] arrangement;
    @SuppressWarnings("FieldCanBeLocal")
    private final boolean optimizeSignals = true;

    @FXML
    private ListView<File> pictureListView;
    @FXML
    private TextArea previewTextArea;
    @FXML
    private Label previewWidth;
    @FXML
    private Label previewHeight;
    @FXML
    private Button mathButton;
    @FXML
    private CheckBox substationsCheckbox;
    @FXML
    private Spinner<Integer> substationOffsetX;
    @FXML
    private Spinner<Integer> substationOffsetY;
    @FXML
    private Slider brightnessSlider;

    @FXML
    private void initialize() {
        pictureList = FXCollections.observableArrayList();
        pictureListView.setItems(pictureList);
    }

    @FXML
    private void open() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File(s)");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.png", "*.jpg", "*.gif", "*.bmp", "*.jpeg", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        List<File> list = fileChooser.showOpenMultipleDialog(pictureListView.getScene().getWindow());
        if (list != null) {
            if (fileChooser.getSelectedExtensionFilter().getDescription().equals("GIF") || list.get(0).getName().endsWith(".gif")) {
                gif(list.get(0));
            } else pictureList.setAll(list);
            if (pictureList.size() > 32) //technique limitation
                pictureList.subList(32, pictureList.size()).clear();
            initPictureMeta(new Image(pictureList.get(0).toURI().toString()));
        }
    }

    private void initPictureMeta(Image image) {
        if (mathButton.isDisabled()) {
            mathButton.setDisable(false);
            substationsCheckbox.setDisable(false);
            substationOffsetX.setDisable(false);
            substationOffsetY.setDisable(false);
            brightnessSlider.setDisable(false);
            pictureListView.setOnKeyPressed(event -> {
                if (event.getCode().isArrowKey()) {
                    if (event.getCode() == KeyCode.DOWN && previousIndex == pictureList.size() - 1) select(0);
                    else if (event.getCode() == KeyCode.UP && previousIndex == 0) select(pictureList.size() - 1);
                    previousIndex = pictureListView.getFocusModel().getFocusedIndex();
                    updatePreview();
                }
            });
        }
        width = (int) image.getWidth();
        height = (int) image.getHeight();
        brightness = 0.5;
        substationsCheckbox.setSelected(true);
        brightnessSlider.setValue(brightness);
        previewWidth.setText("Width: " + width + "px");
        previewHeight.setText("Height: " + height + "px");
        fontSize = 90;
        pictureListView.getFocusModel().focus(0);
        pictureListView.getSelectionModel().select(0);
        pictureListView.requestFocus();
        updatePreview();
    }

    private void select(int index) {
        pictureListView.getSelectionModel().select(index);
        pictureListView.scrollTo(index);
    }

    @FXML
    private void gif(File file) {
        if (!pictureList.isEmpty()) pictureList.clear(); //Clear for import
        try {
            DecodedGif decodedGif = GifDecoder.DecodeGif(file);
            for (var img : decodedGif.Images) {
                var f = File.createTempFile("decodedGif", ".png");
                //Write to file since we need for some reason files in the filesystem instead of Images or Buffered Images
                ImageIO.write(img.image, "png", f);
                pictureList.add(f);
            }
        } catch (IOException e) {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error reading");
            alert.setHeaderText("Can not read file:" + file.getName());
            var sw = new StringWriter();
            var pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            //Write Stacktrace as body
            alert.setContentText(sw.toString());
            alert.show();
        }
    }

    @FXML
    private void math() {
        //calculate raw values from pixels without optimisation
        calculateRawValues();
        //start placing the blueprint entities
        ArrayList<Entity> entities = new ArrayList<>();
        Entity.resetEntityCount();
        //add substations and remove lights
        if (substationsCheckbox.isSelected()) entities.addAll(calculateSubstations());
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
                    if ((!signalValues.contains(arrangement[row][column]) && arrangement[row][column] != 0) || !optimizeSignals)
                        signalValues.add(arrangement[row][column]);
                }
            }
            //place combinators
            if (!SignalLibrary.has(signalValues.size() - 1)) {
                signalValues.subList(valuesLength, signalValues.size()).clear();
                entities.addAll(calculateCombinators(signalValues, column));
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
        final int finalLastLamp = lastLamp;
        if (!signalValues.isEmpty()) entities.addAll(calculateCombinators(signalValues, width));
        //frame control combinator
        entities.add(new ConstantCombinator(.5F, .5F)
                .addFilter(Signal.BLACK, 0, 1)
                .addFilter(Signal.WHITE, 1, 11)
                .addRedConnection(finalLastLamp));
        //blueprint
        Blueprint blueprint = new Blueprint("FactorioDMM-output", entities, SignalLibrary.getIcons(Signal.SMALL_LAMP), 281479271743489L);
        previewTextArea.setFont(Font.font("Consolas Bold", 9.0));
        previewTextArea.setText(BlueprintStringEncoder.Encode(blueprint));
        previewTextArea.setWrapText(true);
        copyMode = true;
        previewTextArea.requestFocus();
        selectAll();
    }

    private void calculateRawValues() {
        arrangement = new Integer[height][width];
        Arrays.stream(arrangement).forEach(a -> Arrays.fill(a, 0));
        if (pictureList != null)
            for (int imageCount = 0; imageCount < pictureList.size() && imageCount < 32; imageCount++) {
                Image image = new Image(pictureList.get(imageCount).toURI().toString());
                PixelReader pixelReader = image.getPixelReader();
                int imageWidth = (int) image.getWidth(), imageHeight = (int) image.getHeight();
                for (int readY = 0; readY < height && readY < imageHeight; readY++)
                    for (int readX = 0; readX < width && readX < imageWidth; readX++)
                        if (pixelReader.getColor(readX, readY).getBrightness() >= brightness)
                            arrangement[readY][readX] = setBit(imageCount, arrangement[readY][readX]);
            }
    }

    private ArrayList<Entity> calculateSubstations() {
        return new ArrayList<>() {{
            for (int subX = substationOffsetX.getValue() + width; subX > -9; subX -= 18)
                for (int subY = substationOffsetY.getValue() + height; subY > -9; subY -= 18) {
                    Entity substation = buildSubstation(subX, subY);
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
        }};
    }

    private Entity buildSubstation(int subX, int subY) {
        EntityBuilder substation = new EntityBuilder("substation", -width + subX * 1F, -height + subY * 1F);
        if (subY > 9) substation.addNeighbour(substation.next_number);
        if (subY != substationOffsetY.getValue() + height) substation.addNeighbour(substation.previous_number);
        else {
            if (subX > 9)
                substation.addNeighbour(substation.entity_number + (int) Math.ceil((substationOffsetY.getValue() + height + 9) / 18.0));
            if (subX != substationOffsetX.getValue() + width)
                substation.addNeighbour(substation.entity_number - (int) Math.ceil((substationOffsetX.getValue() + width + 9) / 18.0));
        }
        return substation.build();
    }

    private ArrayList<Entity> calculateCombinators(ArrayList<Integer> signalValues, int column) {
        float widthOffset = column - width;
        return new ArrayList<>() {{
            ArithmeticCombinator arithmeticCombinator = new ArithmeticCombinator(widthOffset - .5F, 1F);
            add(arithmeticCombinator.setCondition(Signal.EACH, Signal.BLACK, Operation.LEFT_SHIFT, Signal.EACH)
                    .addRedInputConnection(arithmeticCombinator.previous_number)
                    .addGreenInputConnection(arithmeticCombinator.next_number)
                    .addGreenOutputConnection(arithmeticCombinator.previous_number));
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

    @FXML
    private void selectAll() {
        if (!copyMode) return;
        previewTextArea.selectAll();
        previewTextArea.copy();
    }

    @FXML
    @SuppressWarnings("CallToThreadRun")
    private void updatePreview() {
        copyMode = false;
        brightness = brightnessSlider.getValue();
        StringBuilder previewText = new StringBuilder();
        Image image = new Image(pictureListView.getFocusModel().getFocusedItem().toURI().toString());
        PixelReader pixelReader = image.getPixelReader();
        new Thread(() -> {
            for (int readY = 0; readY < height; readY++) {
                for (int readX = 0; readX < width; readX++) {
                    int subY = height - readY + substationOffsetY.getValue() + 18, subX = width - readX + substationOffsetX.getValue() + 18;
                    if (substationsCheckbox.isSelected() && subY % 18 - subY % 2 == 0 && subX % 18 - subX % 2 == 0) {
                        previewText.append("▄▀ ");
                    } else if (readX < image.getWidth() && readY < image.getHeight() && pixelReader.getColor(readX, readY).getBrightness() >= brightness) {
                        previewText.append("██ ");
                    } else previewText.append("░░ ");
                }
                if (readY < height - 1) previewText.append("\n");
            }
        }).run();
        previewTextArea.setFont(Font.font("Consolas Bold", fontSize * .1));
        previewTextArea.setWrapText(false);
        previewTextArea.setText(previewText.toString());
    }

    @FXML
    private void toggleOffsets() {
        if (substationsCheckbox.isSelected()) {
            substationOffsetX.setDisable(false);
            substationOffsetY.setDisable(false);
        } else {
            substationOffsetX.setDisable(true);
            substationOffsetY.setDisable(true);
        }
        updatePreview();
    }

    @FXML
    private void zoom(ScrollEvent scrollEvent) {
        if (copyMode) return;
        int change = (int) Math.ceil(fontSize / 20f);
        previewTextArea.setScrollLeft(0.0);
        if (scrollEvent.getDeltaY() < 0 && fontSize + change <= 700) {
            previewTextArea.setScrollTop(Double.MAX_VALUE);
            fontSize += change;
        } else if (scrollEvent.getDeltaY() > 0 && fontSize - change >= 6) {
            previewTextArea.setScrollTop(0.0);
            fontSize -= change;
        }
        previewTextArea.setFont(Font.font("Consolas Bold", fontSize * .1));
    }

    private static int setBit(int bit, int target) {
        int mask = 1 << (31 - bit);
        return target | mask;
    }
}
