package insar_timur.javafx_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Music extends Application {
    @Override
    public void start(Stage primaryStage) {
        Media media = new Media(getClass().getResource("/insar_timur/javafx_project/animation.gif.mp4").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        Text projectName = new Text("MUSIC PLAYER OF THE CENTURY");
        projectName.setFont(new Font(20));
        projectName.setFill(Color.WHITE);

        VBox vbox = new VBox(10, mediaView, projectName);
        vbox.setStyle("-fx-background-color: black;");
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        StackPane root = new StackPane(vbox);
        root.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(root, 500, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Music Player");
        primaryStage.show();

        mediaPlayer.play();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5), event -> {
                    // После 5 секунд переходим к основной странице
                    mediaPlayer.stop();
                    loadMainPage(primaryStage);
                })
        );
        timeline.play();
    }

    private void loadMainPage(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/music-player.fxml"));
            Scene mainScene = new Scene(fxmlLoader.load(), 400, 500);
            mainScene.getStylesheets().add(getClass().getResource("/insar_timur/javafx_project/style.css").toExternalForm());
            primaryStage.setScene(mainScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
