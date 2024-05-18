module insar_timur.javafx_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens insar_timur.javafx_project to javafx.fxml;
    exports insar_timur.javafx_project;
}
