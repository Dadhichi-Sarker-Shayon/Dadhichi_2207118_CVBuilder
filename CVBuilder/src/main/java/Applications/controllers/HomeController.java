package Applications.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML private Button createButton;

    @FXML
    private void initialize() {
        createButton.setOnAction(e -> openForm());
    }

    private void openForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Applications/form.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) createButton.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/Applications/style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Create CV");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
