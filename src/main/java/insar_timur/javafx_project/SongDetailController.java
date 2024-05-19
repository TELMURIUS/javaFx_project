package insar_timur.javafx_project;

import javafx.fxml.FXML;
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

    private MediaPlayer mediaPlayer;

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

        String songPic = song.getPic();
        String songFilePath = new File(song.getFile_path()).toURI().toString();

        System.out.println("Song PIC: " + songPic);
        Media media = new Media(songFilePath);
        mediaPlayer = new MediaPlayer(media);
        System.out.println("MediaPlayer initialized.");
        System.out.println(song.getFile_path());
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
    void goBack() throws IOException {
        Stage stage = (Stage) albumCover.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("playlist-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 480);
        stage.setScene(scene);
    }
}
