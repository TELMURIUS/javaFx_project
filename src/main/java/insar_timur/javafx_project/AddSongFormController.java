package insar_timur.javafx_project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddSongFormController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField artistField;

    @FXML
    private TextField albumField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField picField;

    private String filePath;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @FXML
    void saveSong() {
        String title = titleField.getText();
        String artist = artistField.getText();
        String album = albumField.getText();
        int releaseYear = Integer.parseInt(yearField.getText());
        String genre = genreField.getText();
        String pic = picField.getText();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO songs (title, artist, album, release_year, genre, pic, file_path) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, artist);
            statement.setString(3, album);
            statement.setInt(4, releaseYear);
            statement.setString(5, genre);
            statement.setString(6, pic);
            statement.setString(7, filePath);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}
