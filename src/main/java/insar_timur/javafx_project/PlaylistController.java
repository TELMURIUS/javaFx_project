package insar_timur.javafx_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PlaylistController {

    @FXML
    private ListView<Song> songListView;

    private List<Song> playlist;

    @FXML
    public void initialize() {
        songListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                playSelectedSong();
            }
        });
    }

    public void setPlaylist(List<Song> playlist) {
        this.playlist = playlist;
        songListView.getItems().setAll(playlist);
    }

    private void playSelectedSong() {
        Song selectedSong = songListView.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            try {
                System.out.println("Selected song: " + selectedSong.getTitle());
                Stage stage = (Stage) songListView.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/song-detail-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 620, 780);
                SongDetailController controller = fxmlLoader.getController();
                controller.setPlaylist(playlist, playlist.indexOf(selectedSong));
                scene.getStylesheets().add(getClass().getResource("/insar_timur/javafx_project/style.css").toExternalForm());
                stage.setScene(scene);
                System.out.println("Scene switched to song detail view");
            } catch (IOException e) {
                System.out.println("Error loading FXML: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    @FXML
    void goBack() {
        try {
            System.out.println("Back button clicked");
            Stage stage = (Stage) songListView.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 780);
            scene.getStylesheets().add(getClass().getResource("/insar_timur/javafx_project/style.css").toExternalForm());
            stage.setScene(scene);
            System.out.println("Scene switched to main view");
        } catch (IOException e) {
            System.out.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
