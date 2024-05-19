module insar_timur.javafx_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;

    opens insar_timur.javafx_project to javafx.fxml;
    exports insar_timur.javafx_project;
}
