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
    private int width;
    private int height;
    private double brightness;
    private double fontSize;
    private boolean copyMode = false;
    private boolean addSubstations = true;
    private boolean optimizeSignals = true;
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
    private Button gifButton;
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
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("All Images", "*.*")
        );
        List<File> list = fileChooser.showOpenMultipleDialog(pictureListView.getScene().getWindow());
        if (list != null) {
            pictureList.setAll(list);
            initPictureMeta(new Image(pictureList.get(0).toURI().toString()));
        }
    }

    private void initPictureMeta(Image image){

        if (mathButton.isDisabled()) {
            mathButton.setDisable(false);
            brightnessSlider.setDisable(false);
            pictureListView.setOnMouseClicked(event -> updatePreview());
            pictureListView.setOnKeyPressed(event -> updatePreview());
        }
        width = (int) image.getWidth();
        height = (int) image.getHeight();
        brightness = 0.5;
        brightnessSlider.setValue(brightness);
        previewWidth.setText("Width: " + width + "px");
        previewHeight.setText("Height: " + height + "px");
        fontSize = 9.0;
        updatePreview();
    }
    @FXML
    private void gif(){
        if(pictureList.size() != 0) pictureList.clear();//Clear for import
        var fc = new FileChooser();
        fc.setTitle("Select Gif");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Gif", "*.gif"));
        var file = fc.showOpenDialog(pictureListView.getScene().getWindow());
        try {
            if (file != null) {
                DecodedGif decodedGif = GifDecoder.DecodeGif(file);
                for (var img : decodedGif.Images) {
                    var f = File.createTempFile("decodedGif",".png");
                    //Write to file since we need for some reason files in the filesystem instead of Images or Buffered Images
                    ImageIO.write(img.image,"png",f);
                    pictureList.add(f);
                }
                initPictureMeta (new Image(pictureList.get(0).toURI().toString()));
            }
        }
        catch (IOException e) {
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
        Entity.setEntityCount(0);
        ArrayList<Entity> entities = new ArrayList<>();
        //add substations and remove lights
        if (addSubstations) entities.addAll(calculateSubstations());
        //optimize values into output
        ArrayList<Integer> signalValues = new ArrayList<>();
        int lastLight = 0;
        boolean placedCombinators = false;
        for (int column = 0; column < width; column++) {
            //check ahead for new signals
            int valuesLength = signalValues.size();
            for (int row = 0; row < height; row++) {
                if (arrangement[row][column] == null) {
                    continue;
                }
                if (!signalValues.contains(arrangement[row][column]) && arrangement[row][column] != 0) {
                    signalValues.add(arrangement[row][column]);
                }
            }
            //place combinators
            if (!SignalID.hasID(signalValues.size() - 1)) {
                signalValues.subList(valuesLength, signalValues.size()).clear();
                entities.addAll(calculateCombinators(signalValues, column));
                signalValues.clear();
                placedCombinators = true;
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
                if (signalValues.contains(arrangement[row][column])) {
                    lamp.setControlBehavior(new ControlBehaviour(true, new CircuitCondition(
                            SignalID.getID(signalValues.indexOf(arrangement[row][column]))
                    )));
                } else if (!optimizeSignals || arrangement[row][column] != 0) {
                    signalValues.add(arrangement[row][column]);
                    lamp.setControlBehavior(new ControlBehaviour(true, new CircuitCondition(
                            SignalID.getID(signalValues.size() - 1)
                    )));
                }
                // connections
                ArrayList<ConnectionData> GreenConnections = new ArrayList<>();
                ArrayList<ConnectionData> RedConnections = new ArrayList<>();
                if (row != 0) GreenConnections.add(new ConnectionData(Entity.getEntityCount() - 1));
                if (row == height - 1) {
                    if (column != 0) {
                        if (!placedCombinators) GreenConnections.add(new ConnectionData(lastLight));
                        RedConnections.add(new ConnectionData(lastLight));
                    }
                    lastLight = Entity.getEntityCount();
                }
                lamp.setConnections(new Connection(new ConnectionPoint(RedConnections, GreenConnections)));
                entities.add(lamp);
            }
            placedCombinators = false;
        }
        int finalLastLight = lastLight;
        entities.addAll(calculateCombinators(signalValues, width));
        //frame control combinator
        entities.add(new Entity(
                "constant-combinator",
                new Position(.5F, .5F),
                0,
                new ControlBehaviour(new ArrayList<>() {{
                    add(new Filter(new SignalID("signal-black"), 0, 1));
                    add(new Filter(new SignalID("signal-white"), 1, 11));
                }}),
                new Connection(new ConnectionPoint(new ArrayList<>() {{
                    add(new ConnectionData(finalLastLight));
                }}, null))
        ));
        //blueprint
        Blueprint blueprint = new Blueprint(
                "FactorioDMM-output",
                entities,
                new Icon[]{new Icon(1, new SignalID("small-lamp"))},
                281479271743489L
        );
        previewTextArea.setFont(Font.font("Comic Sans MS Bold", 9.0));
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
            }}}
        }
    }

    private ArrayList<Entity> calculateSubstations() {
        int offsetX = 1, offsetY = -8;
        return new ArrayList<>() {{
            for (int subX = offsetX + width; subX > -9; subX-=18) {
                for (int subY = offsetY + height; subY > -9; subY-=18) {
                    Entity substation = new Entity(
                            "substation",
                            new Position(-width + subX * 1F, -height + subY * 1F)
                    );
                    // neighbouring substations
                    ArrayList<Integer> neighbours = new ArrayList<>();
                    if (subY > 9) neighbours.add(Entity.getEntityCount() + 1);
                    if (subY != offsetY + height) neighbours.add(Entity.getEntityCount() - 1);
                    else {
                        if (subX > 9) neighbours.add((int) (Entity.getEntityCount() + Math.ceil((offsetY+height+9)/18.0)));
                        if (subX != offsetX + width) neighbours.add((int) (Entity.getEntityCount() - Math.ceil((offsetX+width+9)/18.0)));
                    }
                    substation.setNeighbours(neighbours);
                    add(substation);
                    //remove lights
                    if (subX <= width && subY <= height) {
                        if (subX < width) {
                            if (subX >= 0 && subY >= 0) {
                                arrangement[subY][subX] = null;
                                if (subY-1 >= 0) arrangement[subY-1][subX] = null;
                            }
                        }
                        if (subX-1 >= 0 && subY >= 0) {
                            arrangement[subY][subX-1] = null;
                            if (subY-1 >= 0) arrangement[subY-1][subX-1] = null;
                        }
                    }
                }
            }
        }};
    }

    private ArrayList<Entity> calculateCombinators(ArrayList<Integer> signalValues, int column) {
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
        new Thread(() -> {
            Image image = new Image(pictureList.get(pictureList.indexOf(pictureListView.getFocusModel().getFocusedItem())).toURI().toString());
            PixelReader pixelReader = image.getPixelReader();
            for (int readY = 0; readY < height; readY++) {
                for (int readX = 0; readX < width; readX++) {
                    if (readX < image.getWidth() && readY < image.getHeight() && pixelReader.getColor(readX, readY).getBrightness() >= brightness) {
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
        previewTextArea.setFont(Font.font("Comic Sans MS Bold", fontSize));
        previewTextArea.setWrapText(false);
        previewTextArea.setText(previewText.toString());
    }

    @FXML
    private void zoom(ScrollEvent scrollEvent) {
        if (copyMode) {
            return;
        }
        if (scrollEvent.getDeltaY() < 0 && fontSize < 70.0) {
            fontSize += Math.ceil(fontSize) / 10.0;
            previewTextArea.setFont(Font.font("Comic Sans MS Bold", fontSize));
        } else if (scrollEvent.getDeltaY() > 0 && fontSize > 0.6) {
            fontSize -= Math.ceil(fontSize) / 10.0;
            previewTextArea.setFont(Font.font("Comic Sans MS Bold", fontSize));
        }
        previewTextArea.setScrollTop(0);
    }

    private static int setBit(int bit, int target) {
        int mask = 1 << (31 - bit);
        return target | mask;
    }
}
