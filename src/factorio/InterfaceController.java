package factorio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterfaceController {

    private ObservableList<File> pictureList;
    private int width;
    private int height;
    private double brightness;

    @FXML
    private TextArea previewTextArea;
    @FXML
    private ListView<File> pictureListView;
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
            Image image = new Image(pictureList.get(0).toURI().toString());
            width = (int) image.getWidth();
            height = (int) image.getHeight();
            previewWidth.setText("Width: " + width + "px");
            previewHeight.setText("Height: " + height + "px");
            mathButton.setDisable(false);
            brightnessSlider.setDisable(false);
            brightnessSlider.setValue(50);
            brightness = 0.5;
            updatePreview();
        }
    }

    @FXML
    private void math() {
        //calculate raw values from pixels without optimisation
        int[][] arrangement = new int[height][width];
        if (pictureList != null) {
            int imageCount = 0;
            for (File picture : pictureList) {
                Image image = new Image(picture.toURI().toString());
                if (image.getWidth() != width && image.getHeight() != height) {
                    System.out.println(picture.getName() + " was smaller than the initial resolution");
                    continue;
                }
                System.out.println("Working on " + picture.getName());
                PixelReader pixelReader = image.getPixelReader();
                for (int readY = 0; readY < height; readY++) {
                    for (int readX = 0; readX < width; readX++) {
                        if (pixelReader.getColor(readX, readY).getBrightness() >= brightness) {
                            arrangement[readY][readX] = setBit(imageCount, arrangement[readY][readX]);
                        }
                    }
                }
                imageCount++;
            }
        }

        //optimize values into output
        int[][] outputArrangement = new int[height][width];
        List<Integer> outputSignalValues = new ArrayList<>();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (outputSignalValues.contains(arrangement[row][column])) {
                    outputArrangement[row][column] = outputSignalValues.indexOf(arrangement[row][column]) + 1;
                } else if (arrangement[row][column] != 0) {
                    outputSignalValues.add(arrangement[row][column]);
                    outputArrangement[row][column] = outputSignalValues.size();
                }
            }
            System.out.println(Arrays.toString(outputArrangement[row]));
        }
        System.out.println(outputSignalValues.size() + " : " + outputSignalValues);
    }

    @FXML
    private void updatePreview() {
        brightness = brightnessSlider.getValue() / 100.0;
        previewImage(new Image(pictureList.get(0).toURI().toString()));
    }

    private void previewImage(Image image) {
        StringBuilder previewText = new StringBuilder();
        PixelReader pixelReader = image.getPixelReader();
        new Thread(() -> {
            for (int readY = 0; readY < height; readY++) {
                for (int readX = 0; readX < width; readX++) {
                    if (pixelReader.getColor(readX, readY).getBrightness() >= brightness) {
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
        previewTextArea.setText(previewText.toString());
    }

    private static int setBit(int bit, int target) {
        int mask = 1 << (31 - bit);
        return target | mask;
    }
}
