package insar_timur.javafx_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

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
    private List<Song> playlist;
    private int currentIndex = 0;
    private boolean isLooping = false;
    private boolean isShuffling = false;
    private Random random = new Random();

    public void setPlaylist(List<Song> playlist) {
        this.playlist = playlist;
    }

    public void setSongDetails(Song song) {
        currentIndex = playlist.indexOf(song);
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
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        mediaPlayer = new MediaPlayer(media);
        System.out.println("MediaPlayer initialized.");
        System.out.println(song.getFile_path());

        mediaPlayer.setOnEndOfMedia(() -> {
            if (isLooping) {
                mediaPlayer.seek(mediaPlayer.getStartTime());
                mediaPlayer.play();
            } else {
                playNextSong();
            }
        });
    }

    @FXML
    void play() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    @FXML
    void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @FXML
    void playNextSong() {
        if (isShuffling) {
            currentIndex = random.nextInt(playlist.size());
        } else {
            currentIndex = (currentIndex + 1) % playlist.size();
        }
        playCurrentSong();
    }

    @FXML
    void playPreviousSong() {
        if (isShuffling) {
            currentIndex = random.nextInt(playlist.size());
        } else {
            currentIndex = (currentIndex - 1 + playlist.size()) % playlist.size();
        }
        playCurrentSong();
    }

    @FXML
    void toggleLooping() {
        isLooping = !isLooping;
        System.out.println("Looping is now " + (isLooping ? "enabled" : "disabled"));
    }

    @FXML
    void toggleShuffling() {
        isShuffling = !isShuffling;
        System.out.println("Shuffling is now " + (isShuffling ? "enabled" : "disabled"));
    }

    private void playCurrentSong() {
        Song currentSong = playlist.get(currentIndex);
        setSongDetails(currentSong);
        play();
    }

    @FXML
    void goBack() {
        try {
            System.out.println("Back button clicked");
            Stage stage = (Stage) albumCover.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/playlist-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            scene.getStylesheets().add(getClass().getResource("/insar_timur/javafx_project/style.css").toExternalForm());
            stage.setScene(scene);
            System.out.println("Scene switched to playlist view");
        } catch (IOException e) {
            System.out.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void togglePlayPause() {
        if (mediaPlayer == null) {
            return;
        }

        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.play();
        }

        updatePlayPauseButton();
    }

    private void updatePlayPauseButton() {
        // Update the button text or icon based on the media player's status
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            // Set pause icon or text
            playPauseButton.setText("⏸");
        } else {
            // Set play icon or text
            playPauseButton.setText("▶");
        }
    }
    @FXML
    private void goToInfo() {
        try {
            System.out.println("Info button clicked");
            Stage stage = (Stage) albumCover.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/info-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            scene.getStylesheets().add(getClass().getResource("/insar_timur/javafx_project/style.css").toExternalForm());
            stage.setScene(scene);
            System.out.println("Scene switched to info view");
        } catch (IOException e) {
            System.out.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
