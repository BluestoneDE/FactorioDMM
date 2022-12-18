package factorio.gui;

import factorio.decoders.GifDecoder.DecodedGif;
import factorio.decoders.GifDecoder.GifDecoder;
import factorio.encoders.BlueprintStringEncoder;
import factorio.object.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.input.ScrollEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BitGIFGeneratorController {

    private ObservableList<File> pictureList;
    private int width, height, fontSize;
    private double brightness;
    private boolean copyMode = false, optimizeSignals = true;
    private Integer[][] arrangement;

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
                new FileChooser.ExtensionFilter("All Images", "*.png", "*.jpg", "*.gif", "*.bmp", "*.jpeg", "*.wbmp"),
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
            if (pictureList.size() > 32)
                pictureList.subList(32, pictureList.size()).clear(); //current technique limitation
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
            pictureListView.setOnMousePressed(event -> updatePreview());
            pictureListView.setOnKeyPressed(event -> updatePreview());
            pictureListView.setOnKeyReleased(event -> updatePreview());
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

    @FXML
    private void gif(File file) {
        if (pictureList.size() != 0) pictureList.clear(); //Clear for import
        try {
            DecodedGif decodedGif = GifDecoder.DecodeGif(file);
            for (factorio.decoders.GifDecoder.BufferedImageWithDelay img : decodedGif.Images) {
                File f = File.createTempFile("decodedGif", ".png");
                //Write to file since we need for some reason files in the filesystem instead of Images or Buffered Images
                ImageIO.write(img.image, "png", f);
                pictureList.add(f);
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error reading");
            alert.setHeaderText("Can not read file:" + file.getName());
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
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
        Entity.setEntityCount(0);
        ArrayList<Entity> entities = new ArrayList<>();
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
                entities.addAll(calculateCombinators(signalValues, column));
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
        if (signalValues.size() > 0) entities.addAll(calculateCombinators(signalValues, width));
        //frame control combinator
        entities.add(new Entity(
                "constant-combinator",
                new Position(.5F, .5F),
                0,
                new ControlBehaviour(new ArrayList<Filter>() {{
                    add(new Filter(new SignalID("signal-black"), 0, 1));
                    add(new Filter(new SignalID("signal-white"), 1, 11));
                }}),
                new Connection(new ConnectionPoint(new ArrayList<ConnectionData>() {{
                    add(new ConnectionData(finalLastLamp));
                }}, null))
        ));
        //blueprint
        Blueprint blueprint = new Blueprint(
                "FactorioDMM-output",
                entities,
                new Icon[]{new Icon(1, new SignalID("small-lamp"))},
                281479271743489L
        );
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
        if (pictureList != null) {
            for (int imageCount = 0; imageCount < pictureList.size() && imageCount < 32; imageCount++) {
                Image image = new Image(pictureList.get(imageCount).toURI().toString());
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
    }

    private ArrayList<Entity> calculateSubstations() {
        return new ArrayList<Entity>() {{
            for (int subX = substationOffsetX.getValue() + width; subX > -9; subX -= 18) {
                for (int subY = substationOffsetY.getValue() + height; subY > -9; subY -= 18) {
                    Entity substation = new Entity(
                            "substation",
                            new Position(-width + subX * 1F, -height + subY * 1F)
                    );
                    // neighbouring substations
                    ArrayList<Integer> neighbours = new ArrayList<>();
                    if (subY > 9) neighbours.add(Entity.getEntityCount() + 1);
                    if (subY != substationOffsetY.getValue() + height) neighbours.add(Entity.getEntityCount() - 1);
                    else {
                        if (subX > 9)
                            neighbours.add((int) (Entity.getEntityCount() + Math.ceil((substationOffsetY.getValue() + height + 9) / 18.0)));
                        if (subX != substationOffsetX.getValue() + width)
                            neighbours.add((int) (Entity.getEntityCount() - Math.ceil((substationOffsetX.getValue() + width + 9) / 18.0)));
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

    private ArrayList<Entity> calculateCombinators(ArrayList<Integer> signalValues, int column) {
        float widthOffset = column - width;
        return new ArrayList<Entity>() {{
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
                            new ConnectionPoint(new ArrayList<ConnectionData>() {{
                                add(new ConnectionData(Entity.getEntityCount()));
                            }}, new ArrayList<ConnectionData>() {{
                                add(new ConnectionData(Entity.getEntityCount() + 2));
                            }}),
                            new ConnectionPoint(null, new ArrayList<ConnectionData>() {{
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
                            new Connection(new ConnectionPoint(null, new ArrayList<ConnectionData>() {{
                                add(new ConnectionData(Entity.getEntityCount()));
                            }}))
                    ));
                }
            }
        }};
    }

    @FXML
    private void selectAll() {
        if (copyMode) {
            previewTextArea.selectAll();
            previewTextArea.copy();
        }
    }

    @FXML
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
                    } else {
                        previewText.append("░░ ");
                    }
                }
                if (readY < height - 1) {
                    previewText.append("\n");
                }
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
