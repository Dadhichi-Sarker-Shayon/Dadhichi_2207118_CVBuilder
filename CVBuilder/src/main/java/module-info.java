module com.example.cvbuilder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    exports Applications;
    opens Applications.controllers to javafx.fxml;
    opens Applications.model to javafx.fxml;
    opens Applications to javafx.fxml;
}
