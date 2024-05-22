package insar_timur.javafx_project;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SplashScreenController {
    @FXML
    private ImageView splashImage;

    public void initialize() {
        splashImage.setImage(new Image(getClass().getResourceAsStream("C://Users//Telmurius//Desktop//project_java//javaFx_project//src//main//resources//insar_timur//javafx_project//photo_2024-05-21_01-33-28.jpg")));

        startAnimation();
    }

    private void startAnimation() {
        Timeline timeline = new Timeline();

        // Animation for moving the image from bottom to center
        KeyValue keyValue = new KeyValue(splashImage.translateYProperty(), -200);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);

        timeline.getKeyFrames().add(keyFrame);

        timeline.setOnFinished(event -> showMainScene());

        timeline.play();
    }

    private void showMainScene() {
        // Load the main scene
        Stage stage = (Stage) splashImage.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/music-player.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 780);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
