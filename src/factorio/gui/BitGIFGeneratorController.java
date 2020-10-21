package factorio.gui;

import factorio.encoders.BlueprintStringEncoder;
import factorio.object.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BitGIFGeneratorController {

    private ObservableList<File> pictureList;
    private int width;
    private int height;
    private double brightness;

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
            if (mathButton.isDisabled()) {
                mathButton.setDisable(false);
                brightnessSlider.setDisable(false);
                pictureListView.setOnMouseClicked(event -> updatePreview());
                pictureListView.setOnKeyPressed(event -> updatePreview());
            }
            Image image = new Image(pictureList.get(0).toURI().toString());
            width = (int) image.getWidth();
            height = (int) image.getHeight();
            brightness = 0.5;
            brightnessSlider.setValue(brightness);
            previewWidth.setText("Width: " + width + "px");
            previewHeight.setText("Height: " + height + "px");
            updatePreview();
        }
    }

    @FXML
    private void math() {
        //calculate raw values from pixels without optimisation
        int[][] arrangement = new int[height][width];
        if (pictureList != null) {
            for (int imageCount = 0; imageCount < pictureList.size() && imageCount < 32; imageCount++) {
                Image image = new Image(pictureList.get(imageCount).toURI().toString());
                PixelReader pixelReader = image.getPixelReader();
                for (int readY = 0; readY < height; readY++) {
                    for (int readX = 0; readX < width; readX++) {
                        if (readX < image.getWidth() && readY < image.getHeight() && pixelReader.getColor(readX, readY).getBrightness() >= brightness) {
                            arrangement[readY][readX] = setBit(imageCount, arrangement[readY][readX]);
                        }
                    }
                }
            }
        }

        //optimize values into output
        ArrayList<Entity> entities = new ArrayList<>();
        ArrayList<Integer> signalValues = new ArrayList<>();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                CircuitCondition condition = new CircuitCondition();
                if (signalValues.contains(arrangement[row][column])) {
                    condition.setFirstSignal(new SignalID(signalValues.indexOf(arrangement[row][column])));
                } else if (arrangement[row][column] != 0) {
                    signalValues.add(arrangement[row][column]);
                    condition.setFirstSignal(new SignalID(signalValues.size() - 1));
                }
                ArrayList<ConnectionData> connections = new ArrayList<>();
                //connect up
                if (row != 0) connections.add(new ConnectionData(Entity.getEntityCount() - width + 1));
                //connect left
                if (row == height - 1 && column != 0) connections.add(new ConnectionData(Entity.getEntityCount()));
                entities.add(new Entity(
                        "small-lamp",
                        new Position(-width / 2 + column * 1F, -height + row * 1F),
                        null,
                        null,
                        new ControlBehaviour(true, condition),
                        new Connection(new ConnectionPoint(null, connections))
                ));
            }
        }
        entities.add(new Entity(
                "constant-combinator",
                new Position(-width / 2 + 2F, 0F),
                4,
                null,
                new ControlBehaviour(new ArrayList<Filter>() {{
                    add(new Filter(new SignalID("signal-black", "virtual"), 0, 1));
                }}),
                null
        ));
        entities.add(new Entity(
                "arithmetic-combinator",
                new Position(-width / 2 + 0.5F, 0F),
                6,
                null,
                new ControlBehaviour(new ArithmeticCondition(
                        new SignalID("signal-each", "virtual"),
                        new SignalID("signal-black", "virtual"),
                        "<<",
                        new SignalID("signal-each", "virtual")
                )),
                new Connection(
                        new ConnectionPoint(new ArrayList<ConnectionData>() {{
                            add(new ConnectionData(Entity.getEntityCount() + 2));
                        }}, new ArrayList<ConnectionData>() {{
                            add(new ConnectionData(Entity.getEntityCount()));
                        }}),
                        new ConnectionPoint(null, new ArrayList<ConnectionData>() {{
                            add(new ConnectionData(width * height - width + 1));
                        }})
                )
        ));
        for (int combinator = 0; combinator <= (signalValues.size() - 1) / 18; combinator++) {
            ArrayList<Filter> filters = new ArrayList<>();
            for (int signal = 0; signal < 18 && signal + combinator * 18 < signalValues.size(); signal++) {
                filters.add(new Filter(new SignalID(combinator * 18 + signal), signalValues.get(combinator * 18 + signal), signal + 1));
            }
            entities.add(new Entity(
                    "constant-combinator",
                    new Position(-width / 2 + combinator * 1F, 1F),
                    null,
                    null,
                    new ControlBehaviour(filters),
                    new Connection(
                            new ConnectionPoint(new ArrayList<ConnectionData>() {{
                                add(new ConnectionData(Entity.getEntityCount()));
                            }}, null)
                    )
            ));
        }
        Blueprint blueprint = new Blueprint(
                "FactorioDMM-output",
                entities,
                new Icon[] {new Icon(1, new SignalID("small-lamp"))},
                73019621376L
        );
        previewTextArea.setText(BlueprintStringEncoder.Encode(blueprint));
        previewTextArea.setWrapText(true);
    }

    @FXML
    private void selectAll() {
        previewTextArea.selectAll();
    }

    @FXML
    private void updatePreview() {
        brightness = brightnessSlider.getValue();
        StringBuilder previewText = new StringBuilder();
        new Thread(() -> {
            Image image = new Image(pictureList.get(pictureList.indexOf(pictureListView.getFocusModel().getFocusedItem())).toURI().toString());
            PixelReader pixelReader = image.getPixelReader();
            for (int readY = 0; readY < height; readY++) {
                for (int readX = 0; readX < width; readX++) {
                    if (readX < image.getWidth() && readY < image.getHeight() && pixelReader.getColor(readX, readY).getBrightness() >= brightness) {
                        previewText.append("░░");
                    } else {
                        previewText.append("██");
                    }
                }
                if (readY < height - 1) {
                    previewText.append("\n");
                }
            }
        }).run();
        previewTextArea.setWrapText(false);
        previewTextArea.setText(previewText.toString());
    }

    private static int setBit(int bit, int target) {
        int mask = 1 << (31 - bit);
        return target | mask;
    }
}