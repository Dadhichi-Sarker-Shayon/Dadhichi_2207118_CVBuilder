package Applications.controllers;

import Applications.model.CV;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import java.io.IOException;

public class FormController {

    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextArea addressField;
    @FXML private TextArea educationField;
    @FXML private TextArea skillsField;
    @FXML private TextArea workField;
    @FXML private TextArea projectsField;

    @FXML
    private void handleSubmit() {
        CV cv = new CV(
                fullNameField.getText(),
                emailField.getText(),
                phoneField.getText(),
                addressField.getText(),
                educationField.getText(),
                skillsField.getText(),
                workField.getText(),
                projectsField.getText()
        );

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Applications/preview.fxml"));
            Parent root = loader.load();

            PreviewController previewController = loader.getController();
            previewController.setCV(cv);

            Stage stage = new Stage();
            stage.setTitle("Preview CV");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
