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

    private Scene next;

    public void LoginButtonPressed(ActionEvent actionEvent) {
        openNextScene();
    }

    public void setNextScene(Scene nextScene) {
        next = nextScene;
    }

    public void openNextScene(){
        Stage stage = (Stage) enterButton.getScene().getWindow();
        stage.setScene(next);
    }

}

