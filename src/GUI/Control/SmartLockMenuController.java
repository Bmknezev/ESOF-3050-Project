package GUI.Control;

import GUI.Control.Abstract.AbstractDeviceController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SmartLockMenuController extends AbstractDeviceController {

    @FXML
    private Button ChangePINButton;

    @FXML
    private Button CreateAutomationButton;

    @FXML
    private Button EditAutomationsButton;

    @FXML
    private ImageView SmartDeviceImageView;

    @FXML
    private Label SmartDeviceNameLabel;

    @FXML
    private Label StatusIndicatorLabel;

    @FXML
    private Button TempUnlockButton;

    @FXML
    private TextField TempUnlockTextField;

    @FXML
    private Button ToggleLockStatusButton;

    @FXML
    private Button backButton;

    private Scene previous;
    // this is just a default object to test the GUI

    private int deviceID;


    public void setPreviousScene(Scene previousScene) {
        previous = previousScene;
    }
    public void BackButtonPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previous);

    }

    @Override
    public void update(String[] s) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                deviceID = Integer.parseInt(s[0]);
                SmartDeviceNameLabel.setText(s[1]);
                SmartDeviceImageView.setImage(Boolean.parseBoolean(s[2]) ? new javafx.scene.image.Image("/GUI/Images/Lock Icon.png") : new javafx.scene.image.Image("/GUI/Images/Lock Icon.png"));
                StatusIndicatorLabel.setText(Boolean.parseBoolean(s[2]) ? "Locked" : "Unlocked");
            }
        });

    }

    @FXML
    void ChangePINButtonPressed(ActionEvent event) {

    }

    @FXML
    void CreateAutomationButtonPressed(ActionEvent event) {

    }

    @FXML
    void EditAutomationButtonPressed(ActionEvent event) {

    }

    @FXML
    void TempUnlockButtonPressed(ActionEvent event) {

    }

    @FXML
    void ToggleLockStatusButtonPressed(ActionEvent event) {
        if(StatusIndicatorLabel.getText().equals("Locked")){
            StatusIndicatorLabel.setText("Unlocked");
            SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/Lock Icon.png"));
            UpdateServer("lockStatus|false");
        }
        else{
            StatusIndicatorLabel.setText("Locked");
            SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/Lock Icon.png"));
            UpdateServer("lockStatus|true");
        }

    }

    private void UpdateServer(String msg){
        String message = 0 + "@" + deviceID + "@" + msg;
        try {
            super.client.sendToServer(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void backButtonPressed(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previous);
    }
}
