package factorio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("interface.fxml")), 764, 541));
        primaryStage.setTitle("Factorio Display Math Machine");
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(360);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}