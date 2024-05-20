package insar_timur.javafx_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.IOException;

public class MusicPlayer {

    @FXML
    private Label chooseMusic;

    @FXML
    private Label songTitle;

    @FXML
    private Label songArtist;

    @FXML
    private ImageView albumCover;

    private MediaPlayer mediaPlayer;

    public MusicPlayer() {
    }

    @FXML
    void chooseMusic(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select your music");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            playSong(file.toURI().toString(), file.getName(), "", "", null);
        }
    }

    @FXML
    void pause(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    void play(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    @FXML
    void stop(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @FXML
    void goToPlaylist(MouseEvent event) throws IOException {
        Stage stage = (Stage) chooseMusic.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/playlist-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 780);
        stage.setScene(scene);
    }

    public void playSong(String songUri, String title, String artist, String album, String picUrl) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        Media media = new Media(songUri);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnReady(() -> {
            chooseMusic.setText(title);
            songTitle.setText(title);
            songArtist.setText(artist);
            if (picUrl != null) {
                albumCover.setImage(new Image(picUrl));
            }
            mediaPlayer.play();
        });
    }

    @FXML
    void goBack() {
        try {
            System.out.println("Back button clicked");
            Stage stage = (Stage) albumCover.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/playlist-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 780);
            scene.getStylesheets().add(getClass().getResource("/insar_timur/javafx_project/style.css").toExternalForm());
            stage.setScene(scene);
            System.out.println("Scene switched to playlist view");
        } catch (IOException e) {
            System.out.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
