package factorio.gui;

import factorio.calculator.FactorioCalculator;
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
import java.util.List;

public class BitGIFGeneratorController {

    private ObservableList<File> pictureList;
    private int width, height, fontSize;
    private double brightness;
    private boolean copyMode = false, optimizeSignals = true;
    private FactorioCalculator factorioCalculator;

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
        factorioCalculator = new FactorioCalculator();
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
            for (var img : decodedGif.Images) {
                var f = File.createTempFile("decodedGif", ".png");
                //Write to file since we need for some reason files in the filesystem instead of Images or Buffered Images
                ImageIO.write(img.image, "png", f);
                pictureList.add(f);
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
        try {
            //blueprint
            Blueprint blueprint = factorioCalculator.CalculateBluePrintWithFile(height, width, brightness,substationOffsetX.getValue(), substationOffsetY.getValue(),substationsCheckbox.isSelected(), optimizeSignals, pictureList);
            previewTextArea.setFont(Font.font("Consolas Bold", 9.0));
            previewTextArea.setText(BlueprintStringEncoder.Encode(blueprint));
            previewTextArea.setWrapText(true);
            copyMode = true;
            previewTextArea.requestFocus();
            selectAll();
        }
        catch (FileNotFoundException e){
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error reading");
            alert.setHeaderText("Can not read file");
            var sw = new StringWriter();
            var pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            //Write Stacktrace as body
            alert.setContentText(sw.toString());
            alert.show();
        }
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

}
