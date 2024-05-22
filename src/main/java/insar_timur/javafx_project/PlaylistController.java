package insar_timur.javafx_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlaylistController {

    @FXML
    private ListView<Song> songListView;

    @FXML
    private Button backButton;

    private ObservableList<Song> songList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadSongsFromDatabase();
        songListView.setItems(songList);
        songListView.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {
            @Override
            public ListCell<Song> call(ListView<Song> param) {
                return new ListCell<Song>() {
                    @Override
                    protected void updateItem(Song song, boolean empty) {
                        super.updateItem(song, empty);
                        if (empty || song == null) {
                            setText(null);
                        } else {
                            setText(song.getTitle() + " - " + song.getArtist());
                        }
                    }
                };
            }
        });

        songListView.setOnMouseClicked(event -> {
            Song selectedSong = songListView.getSelectionModel().getSelectedItem();
            if (selectedSong != null) {
                playSelectedSong(selectedSong);
            }
        });
    }

    private void loadSongsFromDatabase() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM songs");

            while (resultSet.next()) {
                Song song = new Song(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("artist"),
                        resultSet.getString("album"),
                        resultSet.getInt("release_year"),
                        resultSet.getString("genre"),
                        resultSet.getString("pic"),
                        resultSet.getString("file_path")
                );
                songList.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void playSelectedSong(Song song) {
        try {
            System.out.println("Selected song: " + song.getTitle());
            Stage stage = (Stage) songListView.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/song-detail-view.fxml"));
            Parent root = loader.load();

            SongDetailController controller = loader.getController();
            System.out.println("Controller loaded, setting song details...");
            controller.setPlaylist(songList);
            controller.setSongDetails(song);
            System.out.println("Song details set, switching scene...");
            Scene scene = new Scene(root, 400, 500);
            scene.getStylesheets().add(getClass().getResource("/insar_timur/javafx_project/style.css").toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack() {
        try {
            System.out.println("Back button clicked");
            Stage stage = (Stage) backButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/insar_timur/javafx_project/music-player.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            scene.getStylesheets().add(getClass().getResource("/insar_timur/javafx_project/style.css").toExternalForm());
            stage.setScene(scene);
            System.out.println("Scene switched to music player");
        } catch (IOException e) {
            System.out.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
