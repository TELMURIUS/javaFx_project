package insar_timur.javafx_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private File selectedFile;

    public MusicPlayer() {
    }

    @FXML
    void chooseMusic(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select your music");
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            playSong(selectedFile.toURI().toString(), selectedFile.getName(), "", "", null);
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
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setScene(scene);
    }

    @FXML
    void openAddForm(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/add-song-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);

        AddSongFormController controller = fxmlLoader.getController();
        if (selectedFile != null) {
            controller.setFilePath(selectedFile.getAbsolutePath());
        }

        stage.showAndWait();
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
}
