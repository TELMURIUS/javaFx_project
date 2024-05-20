package insar_timur.javafx_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    private List<Song> playlist;
    private int currentIndex;

    public void setSongDetails(Song song) {
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

    public void setPlaylist(List<Song> playlist, int currentIndex) {
        this.playlist = playlist;
        this.currentIndex = currentIndex;
        setSongDetails(playlist.get(currentIndex));
    }

    @FXML
    void playPause() {
        toggle();
    }

    @FXML
    private void toggle() {
        if (mediaPlayer != null) {
            if (isPlaying) {
                mediaPlayer.pause();
                playPauseButton.setText("⏯");
            } else {
                mediaPlayer.play();
                playPauseButton.setText("⏸");
            }
            isPlaying = !isPlaying;
        }
    }

    @FXML
    void next() {
        if (playlist != null && currentIndex > 0) {
            setSongDetails(playlist.get(currentIndex++));
            playPause();
        }
    }


    @FXML
    void previous() {
        if (playlist != null && currentIndex > 0) {
            setSongDetails(playlist.get(--currentIndex));
            playPause();
        }
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

    @FXML
    void info() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/info-window.fxml"));
            VBox root = loader.load();
            Stage infoStage = new Stage();
            infoStage.setTitle("Song Info");
            infoStage.setScene(new Scene(root));
            infoStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
