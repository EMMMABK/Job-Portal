module org.example.jobportal {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.jobportal to javafx.fxml;
    exports org.example.jobportal;
}