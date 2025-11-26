package Applications.controllers;

import Applications.model.CV;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class PreviewController {

    @FXML private Label fullNameLabel, emailLabel, phoneLabel, addressLabel;
    @FXML private VBox educationVBox, skillsVBox, workVBox, projectsVBox;
    @FXML private Button editButton, homeButton;
    @FXML private ImageView profileImage;

    private FormController formController;
    private CV originalCV;

    public void setFormController(FormController controller) { this.formController = controller; }

    public void setCV(CV cv) {
        this.originalCV = cv;
        fullNameLabel.setText(cv.getFullname());
        emailLabel.setText(cv.getEmail());
        phoneLabel.setText(cv.getPhone());
        addressLabel.setText(cv.getAddress());
        populateVBox(educationVBox, cv.getEducation());
        populateVBox(skillsVBox, cv.getSkills());
        populateVBox(workVBox, cv.getWorkExperience());
        populateVBox(projectsVBox, cv.getProject());
        if (cv.getProfileImagePath() != null) {
            Image img = new Image(cv.getProfileImagePath(), 100, 100, true, true);
            profileImage.setImage(img);
            profileImage.setClip(new Circle(50, 50, 50));
        }
    }

    private void populateVBox(VBox container, String data) {
        container.getChildren().clear();
        if (data != null && !data.isEmpty()) {
            for (String line : data.split("\n")) {
                Label lbl = new Label(line);
                lbl.setWrapText(true);
                container.getChildren().add(lbl);
            }
        }
    }

    @FXML private void handleEdit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Applications/form.fxml"));
            Parent root = loader.load();
            FormController controller = loader.getController();
            controller.setCV(originalCV);
            Stage stage = (Stage) editButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) { e.printStackTrace(); }
    }

    @FXML private void handleGoHome() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Applications/Home.fxml"));
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) { e.printStackTrace(); }
    }
}
