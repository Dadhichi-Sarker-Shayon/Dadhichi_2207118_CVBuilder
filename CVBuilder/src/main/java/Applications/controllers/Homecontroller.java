package Applications.controllers;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;


public class Homecontroller {
    @FXML
    private void CreateCV(ActionEvent event) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("D:\\Advanced programming lab assignment\\CVBuilder\\src\\main\\resources\\Applications\\form.fxml"));
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("CV Builder");
        stage.show();
    }
}