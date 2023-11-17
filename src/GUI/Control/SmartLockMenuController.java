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
import messages.AbstractDeviceMessage;
import messages.server.LockMessage;

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
    public void update(AbstractDeviceMessage msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                LockMessage message = (LockMessage) msg;
                SmartDeviceNameLabel.setText(message.getName());
                deviceID = message.getDeviceID();
                if(message.getLockStatus()){
                    StatusIndicatorLabel.setText("Locked");
                    SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/Lock Icon.png"));
                    ToggleLockStatusButton.setText("Unlock");
                }
                else{
                    StatusIndicatorLabel.setText("Unlocked");
                    SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/Lock Icon.png"));
                    ToggleLockStatusButton.setText("Lock");
                }

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
            UpdateServer();
        }
        else{
            StatusIndicatorLabel.setText("Locked");
            SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/Lock Icon.png"));
            UpdateServer();
        }

    }

    private void UpdateServer(){
        LockMessage message = new LockMessage(deviceID,SmartDeviceNameLabel.getText(), StatusIndicatorLabel.getText().equals("Locked"), 0, 0);
        client.UpdateServer(message);
    }

    @FXML
    void backButtonPressed(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previous);
    }
}
