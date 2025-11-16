module com.example.cvbuilder {
    requires javafx.controls;
    requires javafx.fxml;

    exports Applications;
    opens Applications.controllers to javafx.fxml;
}
