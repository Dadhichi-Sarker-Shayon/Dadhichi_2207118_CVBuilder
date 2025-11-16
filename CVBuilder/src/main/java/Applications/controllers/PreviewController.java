package Applications.controllers;

import Applications.model.CV;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PreviewController {

    @FXML private Label fullNameLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private Label addressLabel;
    @FXML private Label educationLabel;
    @FXML private Label skillsLabel;
    @FXML private Label workLabel;
    @FXML private Label projectsLabel;

    public void setCV(CV cv) {
        fullNameLabel.setText(cv.getFullname());
        emailLabel.setText(cv.getEmail());
        phoneLabel.setText(cv.getPhone());
        addressLabel.setText(cv.getAddress());
        educationLabel.setText(cv.getEducation());
        skillsLabel.setText(cv.getSkills());
        workLabel.setText(cv.getWorkExperience());
        projectsLabel.setText(cv.getProject());
    }
}
