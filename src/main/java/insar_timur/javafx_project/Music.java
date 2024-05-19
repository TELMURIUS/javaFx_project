package insar_timur.javafx_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Music extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/music-player.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 780);

        scene.getStylesheets().add(getClass().getResource("/insar_timur/javafx_project/style.css").toExternalForm());

        stage.setTitle("Music Player");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
