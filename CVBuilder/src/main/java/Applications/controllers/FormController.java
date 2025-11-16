package Applications.controllers;

import Applications.model.CV;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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

    @FXML private Button generateButton;
    @FXML private Button homeButton;

    @FXML
    private void initialize() {
        generateButton.setOnAction(e -> handleGenerate());
        homeButton.setOnAction(e -> goHome());
    }

    private void goHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Applications/home.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) homeButton.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/Applications/style.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("CV Builder");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleGenerate() {
        if (!validate()) return;

        CV cv = new CV(
                fullNameField.getText().trim(),
                emailField.getText().trim(),
                phoneField.getText().trim(),
                addressField.getText().trim(),
                educationField.getText().trim(),
                skillsField.getText().trim(),
                workField.getText().trim(),
                projectsField.getText().trim()
        );

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Applications/preview.fxml"));
            Parent root = loader.load();

            PreviewController previewController = loader.getController();
            previewController.setCV(cv);

            Stage stage = new Stage();
            stage.setTitle("CV Preview");
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/Applications/style.css").toExternalForm());
            stage.setScene(scene);
            stage.initOwner(generateButton.getScene().getWindow());
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean validate() {
        if (fullNameField.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation error", "Full Name is required.");
            return false;
        }
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation error", "Email is required.");
            return false;
        }
        if (!email.contains("@") || !email.contains(".")) {
            showAlert(Alert.AlertType.WARNING, "Validation warning", "Please enter a valid email.");
            return false;
        }
        return true;
    }

    private void showAlert(Alert.AlertType t, String title, String msg) {
        Alert a = new Alert(t);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
