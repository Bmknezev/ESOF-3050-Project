package GUI.Control;

import ClientServer.SmartHomeClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import messages.LoginMessage;

import static java.lang.Thread.sleep;

public class LoginMenuController {

    public Button quitButton;
    @FXML
    private TextField emailTextField;

    @FXML
    private Button enterButton;

    @FXML
    private TextField passwordTextField;

    private Scene next;
    private Stage stage;
    private SmartHomeClient client;

    public void LoginButtonPressed(ActionEvent actionEvent) {

        try {
            client.sendToServer(new messages.LoginMessage(emailTextField.getText(), passwordTextField.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addServer(SmartHomeClient server){
        this.client = server;
    }

    public void login(LoginMessage msg){
        System.out.println("Login status: " + msg.getLoginStatus());
        if (msg.getLoginStatus())
            openNextScene();
        else{
            Platform.runLater(() -> {
                enterButton.setText("Invalid Credentials");
                enterButton.setStyle("-fx-text-fill: red;");

            });
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(() -> enterButton.setText("Enter"));
            enterButton.setStyle("-fx-text-fill: black;");
        }
    }

    public void setNextScene(Scene nextScene, Stage s) {
        stage = s;
        next = nextScene;
    }

    public void openNextScene(){
        System.out.println("Opening next scene.");
        try{
            Platform.runLater(() -> stage.setScene(next));
            //stage.setScene(next);
        } catch (Exception e){
            System.out.println("Error opening next scene.");
            e.printStackTrace();
        }
        //stage.setScene(next);
    }

    public void quitButtonPressed(ActionEvent actionEvent) {
        Platform.exit();
    }
}

