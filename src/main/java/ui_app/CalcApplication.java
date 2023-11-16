package ui_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CalcApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image(Objects.requireNonNull(CalcApplication.class.getResourceAsStream("icon.png"))));
        FXMLLoader fxmlLoader = new FXMLLoader(CalcApplication.class.getResource("calc-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 420);
        scene.getStylesheets().add(Objects.requireNonNull(CalcApplication.class.getResource("global.css")).toExternalForm());
        stage.setTitle("Commsult Calculator Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}