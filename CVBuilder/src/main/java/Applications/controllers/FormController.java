package Applications.controllers;

import Applications.Database.DatabaseHelper;
import Applications.model.CV;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FormController {

    @FXML private TextField fullNameField, emailField, phoneField;
    @FXML private TextArea addressField;
    @FXML private VBox educationList, skillsList, workList, projectsList;
    @FXML private ImageView profilePreview;

    private String selectedPhotoPath = null;
    private CV cv;

    public void setCV(CV cv) {
        this.cv = cv;
        populateFields();
    }

    private void populateFields() {
        if (cv == null) return;
        fullNameField.setText(cv.getFullname());
        emailField.setText(cv.getEmail());
        phoneField.setText(cv.getPhone());
        addressField.setText(cv.getAddress());
        populateVBox(educationList, cv.getEducation());
        populateVBox(skillsList, cv.getSkills());
        populateVBox(workList, cv.getWorkExperience());
        populateVBox(projectsList, cv.getProject());
        if (cv.getProfileImagePath() != null) {
            Image img = new Image(cv.getProfileImagePath(), 120, 120, true, true);
            profilePreview.setImage(img);
            profilePreview.setClip(new Circle(60, 60, 60));
            selectedPhotoPath = cv.getProfileImagePath();
        }
    }

    private void populateVBox(VBox box, String data) {
        box.getChildren().clear();
        if (data != null && !data.isEmpty()) {
            for (String line : data.split("\n")) {
                TextField tf = new TextField(line);
                box.getChildren().add(tf);
            }
        }
    }

    @FXML private void addEducation() { addTextField(educationList, "Enter degree / qualification"); }
    @FXML private void addSkill() { addTextField(skillsList, "Enter a skill"); }
    @FXML private void addWorkExperience() { addTextField(workList, "Enter work experience"); }
    @FXML private void addProject() { addTextField(projectsList, "Enter project"); }

    private void addTextField(VBox box, String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        box.getChildren().add(tf);
    }

    @FXML private void handleUploadPhoto() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Profile Photo");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File file = chooser.showOpenDialog(null);
        if (file != null) {
            selectedPhotoPath = file.toURI().toString();
            Image img = new Image(selectedPhotoPath, 120, 120, true, true);
            profilePreview.setImage(img);
            profilePreview.setClip(new Circle(60, 60, 60));
        }
    }

    @FXML
    private void handleSubmit() {
        if (fullNameField.getText().isEmpty() || emailField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill in all required fields.");
            return;
        }

        boolean isEditing = cv != null && cv.getId() > 0;

        CV newCV = new CV(
                isEditing ? cv.getId() : -1,
                fullNameField.getText(),
                emailField.getText(),
                phoneField.getText(),
                addressField.getText(),
                getVBoxText(educationList),
                getVBoxText(skillsList),
                getVBoxText(workList),
                getVBoxText(projectsList),
                selectedPhotoPath
        );

        boolean success;
        if (isEditing) {
            success = DatabaseHelper.updateCV(
                    newCV.getId(),
                    newCV.getFullname(),
                    newCV.getEmail(),
                    newCV.getPhone(),
                    newCV.getAddress(),
                    newCV.getEducation(),
                    newCV.getSkills(),
                    newCV.getWorkExperience(),
                    newCV.getProject(),
                    newCV.getProfileImagePath()
            );
        } else {
            int generatedId = DatabaseHelper.insertCVAndReturnId(
                    newCV.getFullname(),
                    newCV.getEmail(),
                    newCV.getPhone(),
                    newCV.getAddress(),
                    newCV.getEducation(),
                    newCV.getSkills(),
                    newCV.getWorkExperience(),
                    newCV.getProject(),
                    newCV.getProfileImagePath()
            );
            if (generatedId > 0) {
                newCV.setId(generatedId);
                success = true;
            } else success = false;
        }

        if (!success) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to save CV.");
            return;
        }

        this.cv = newCV;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Applications/Preview.fxml"));
            Parent root = loader.load();
            PreviewController previewController = loader.getController();
            previewController.setCV(newCV);
            previewController.setFormController(this);

            Stage stage = (Stage) fullNameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to open preview: " + e.getMessage());
        }
    }


    private String getVBoxText(VBox box) {
        StringBuilder sb = new StringBuilder();
        for (var node : box.getChildren()) {
            if (node instanceof TextField tf && !tf.getText().isEmpty()) sb.append(tf.getText()).append("\n");
        }
        return sb.toString().trim();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
