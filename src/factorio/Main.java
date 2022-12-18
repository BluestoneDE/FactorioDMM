package factorio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("gui/BitGIFGenerator.fxml")), 764, 541));
        primaryStage.setTitle("Factorio Display Math Machine");
        primaryStage.getIcons().add(new Image("/fdmm.png"));
        primaryStage.setMinWidth(440);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
