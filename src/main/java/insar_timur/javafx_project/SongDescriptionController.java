package insar_timur.javafx_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class SongDescriptionController {

    @FXML
    private Label songTitle;

    @FXML
    private Label songArtist;

    @FXML
    private Label songAlbum;

    @FXML
    private Label songGenre;

    private Song currentSong;

    public void initialize() {
        // Инициализация контроллера
    }

    public void setSongDetails(Song song) {
        currentSong = song;
        songTitle.setText(song.getTitle());
        songArtist.setText(song.getArtist());
        songAlbum.setText(song.getAlbum());
        songGenre.setText(song.getGenre());
    }

    @FXML
    void goBack() {
        try {
            Stage stage = (Stage) songTitle.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/song-detail-view.fxml"));
            Scene scene = new Scene(loader.load(), 600, 700);
            scene.getStylesheets().add(getClass().getResource("/insar_timur/javafx_project/style.css").toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
