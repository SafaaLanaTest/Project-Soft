module com.example.sw_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sw_project to javafx.fxml;
    exports com.example.sw_project;
}