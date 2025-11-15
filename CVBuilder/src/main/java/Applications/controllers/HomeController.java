package Applications.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;

public class HomeController {

    @FXML
    private Button startButton;

    @FXML
    private void initialize() {
        startButton.setOnAction(e -> openForm());
    }

    private void openForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Applications/form.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
