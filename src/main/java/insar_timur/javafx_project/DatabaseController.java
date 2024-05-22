package insar_timur.javafx_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.*;

public class DatabaseController {

    @FXML
    private Label dataLabel;

    @FXML
    public void initialize() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM your_table");

            StringBuilder data = new StringBuilder();
            while (resultSet.next()) {
                data.append(resultSet.getString("your_column")).append("\n");
            }
            dataLabel.setText(data.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public class SongDao {
        private static final String DB_URL = "jdbc:postgresql://localhost:5432/media_player";
        private static final String DB_USER = "postgres";
        private static final String DB_PASSWORD = "postgres";

        public void save(Song song) {
            String sql = "INSERT INTO songs (title, artist, album) VALUES (?, ?, ?)";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, song.getTitle());
                statement.setString(2, song.getArtist());
                statement.setString(3, song.getAlbum());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public Song findById(int id) {
            String sql = "SELECT * FROM songs WHERE id = ?";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapRow(resultSet);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        private Song mapRow(ResultSet resultSet) throws SQLException {
            Song song = new Song();
            song.setId(resultSet.getInt("id"));
            song.setTitle(resultSet.getString("title"));
            song.setArtist(resultSet.getString("artist"));
            song.setAlbum(resultSet.getString("album"));
            return song;
        }
    }


}
