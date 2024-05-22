package insar_timur.javafx_project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.IOException;

public class SongDetailController {

    @FXML
    private ImageView albumCover;

    @FXML
    private Label songTitle;

    @FXML
    private Label songArtist;

    @FXML
    private Label songAlbum;

    @FXML
    private Label songGenre;

    @FXML
    private Button playPauseButton;

    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private Song currentSong;




    public void setSongDetails(Song song) {
        currentSong = song;
        System.out.println("Setting song details for: " + song.getTitle());
        songTitle.setText(song.getTitle());
        songArtist.setText(song.getArtist());
        songAlbum.setText(song.getAlbum());
        songGenre.setText(song.getGenre());
        if (song.getPic() != null) {
            System.out.println("Setting album cover...");
            albumCover.setImage(new Image(song.getPic()));
        }

        String songUri = new File(song.getFile_path()).toURI().toString();
        System.out.println("Song URI: " + songUri);
        Media media = new Media(songUri);
        mediaPlayer = new MediaPlayer(media);
        System.out.println("MediaPlayer initialized.");
        System.out.println(song.getFile_path());
    }

    @FXML
    private void togglePlayPause() {
        if (mediaPlayer == null) {
            return;
        }

        if (isPlaying) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.play();
        }

        isPlaying = !isPlaying;
        updatePlayPauseButton();
    }

    @FXML
    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        isPlaying = false;
        updatePlayPauseButton();
    }

    private int currentSongIndex; // Объявляем переменную для отслеживания текущего индекса выбранной песни



    private void updatePlayPauseButton() {
        if (isPlaying) {
            playPauseButton.setText("⏸");
        } else {
            playPauseButton.setText("▶");
        }
    }

    @FXML
    private void goBack() {
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


    @FXML
    private void goBackPlaylist() {
        try {
            System.out.println("Back button clicked");
            Stage stage = (Stage) albumCover.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("javaFx_project//src//main//resources//insar_timur//javafx_project//song-detail-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 780);
            scene.getStylesheets().add(getClass().getResource("/insar_timur/javafx_project/style.css").toExternalForm());
            stage.setScene(scene);
            System.out.println("Scene switched to playlist view");
        } catch (IOException e) {
            System.out.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void goToInfo() {
        try {
            System.out.println("Info button clicked");
            Stage stage = (Stage) albumCover.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/info-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 780);
            scene.getStylesheets().add(getClass().getResource("/insar_timur/javafx_project/style.css").toExternalForm());
            stage.setScene(scene);
            System.out.println("Scene switched to info view");
        } catch (IOException e) {
            System.out.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

