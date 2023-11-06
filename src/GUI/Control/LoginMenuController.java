package GUI.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginMenuController {

    @FXML
    private TextField emailTextField;

    @FXML
    private Button enterButton;

    @FXML
    private TextField passwordTextField;

    private Scene second;

    public void LoginButtonPressed(ActionEvent actionEvent) {
        openSecondScene();
    }

    public void setSecondScene(Scene secondScene) {
        second = secondScene;
    }

    public void openSecondScene(){
        Stage stage = (Stage) enterButton.getScene().getWindow();
        stage.setScene(second);
    }

}

