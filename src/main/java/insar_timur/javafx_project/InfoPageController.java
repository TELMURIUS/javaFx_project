package insar_timur.javafx_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class InfoPageController {

    private Stage stage;

    @FXML
    private Button backButton;

    @FXML
    private Label songTitle;

    @FXML
    private Label songArtist;

    @FXML
    private Label songAlbum;

    @FXML
    private Label songGenre;

    @FXML
    private Label songYear;

    public Song currentSong;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
        updateSongInfo();
    }

    public void initialize() {
        updateSongInfo();
    }

    public void updateSongInfo() {
        if (currentSong != null) {
            songTitle.setText("Title: " + currentSong.getTitle());
            songArtist.setText("Artist: " + currentSong.getArtist());
            songAlbum.setText("Album: " + currentSong.getAlbum());
            songGenre.setText("Genre: " + currentSong.getGenre());
            songYear.setText("Year: " + currentSong.getReleaseYear());
        } else {
            clearSongInfo();
        }
    }

    public void clearSongInfo() {
        songTitle.textProperty().unbind();
        songArtist.textProperty().unbind();
        songAlbum.textProperty().unbind();
        songGenre.textProperty().unbind();
        songYear.textProperty().unbind();
        songTitle.setText("Title: ");
        songArtist.setText("Artist: ");
        songAlbum.setText("Album: ");
        songGenre.setText("Genre: ");
        songYear.setText("Year: ");
    }


    @FXML
    public void goBack() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/song-detail-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 780);
            scene.getStylesheets().add(getClass().getResource("/insar_timur/javafx_project/style.css").toExternalForm());
            stage.setScene(scene);
            System.out.println("Scene switched to music player");
        } catch (IOException e) {
            System.out.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
