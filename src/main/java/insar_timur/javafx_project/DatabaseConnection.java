package insar_timur.javafx_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/media_player";
        String user = "postgres";
        String password = "postgres";

        return DriverManager.getConnection(url, user, password);
    }
}
