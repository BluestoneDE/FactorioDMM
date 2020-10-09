package factorio;

import com.google.gson.Gson;
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
import java.util.Arrays;
import java.util.List;

public class Controller {

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
        StringBuilder outputBlueprint = new StringBuilder(); //todo remove this debug output
        int[][] outputArrangement = new int[height][width]; //todo remove this debug array
        Entity.resetEntityCount();
        Entity[] entities = new Entity[width*height];
        ArrayList<Integer> outputSignalValues = new ArrayList<>();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                entities[row * width + column] = new Entity(
                        "small-lamp",
                        new Position(width / 2 - width + column + 1F, height / 2 - height + row + 1F),
                        null,
                        null,
                        null,
                        null
                );
                if (outputSignalValues.contains(arrangement[row][column])) {
                    outputArrangement[row][column] = outputSignalValues.indexOf(arrangement[row][column]) + 1;
                } else if (arrangement[row][column] != 0) {
                    outputSignalValues.add(arrangement[row][column]);
                    outputArrangement[row][column] = outputSignalValues.size();
                }
            }
            outputBlueprint.append(Arrays.toString(outputArrangement[row])).append("\n");
        }
        outputBlueprint.append(outputSignalValues.size()).append(" ").append(outputSignalValues);
        //previewTextArea.setText(outputBlueprint.toString());
        Blueprint blueprint = new Blueprint(
                "FactorioDMM output",
                entities,
                new Icon[] {
                        new Icon(1, new SignalID("small-lamp"))
                },
                73019621376L
        );
        previewTextArea.setText("{\"blueprint\":" + new Gson().toJson(blueprint) + "}");
        previewTextArea.setFont(new javafx.scene.text.Font("Comic Sans MS BOLD", 18));
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
        previewTextArea.setFont(new javafx.scene.text.Font("Comic Sans MS BOLD", 9));
        previewTextArea.setText(previewText.toString());
    }

    private static int setBit(int bit, int target) {
        int mask = 1 << (31 - bit);
        return target | mask;
    }
}
