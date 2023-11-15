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
import messages.GarageDoorMessage;

public class SmartGarageDoorOpenerMenuController extends AbstractDeviceController {

    @FXML
    private Button ChangePINButton;

    @FXML
    private Button CreateAutomationButton;

    @FXML
    private Button EditAutomationsButton;

    @FXML
    private Button backButton;

    @FXML
    private ImageView SmartDeviceImageView;

    @FXML
    private Label SmartDeviceNameLabel;

    @FXML
    private Label StatusIndicatorLabel;

    @FXML
    private Button TempOpenButton;

    @FXML
    private TextField TempOpenTextField;

    @FXML
    private Button ToggleGarageDoorStatusButton;

    private Scene previous;
    // this is just a default object to test the GUI

    private int deviceID;


    public void setPreviousScene(Scene previousScene) {
        previous = previousScene;
    }

    public void backButtonPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previous);

    }

    @Override
    public void update(AbstractDeviceMessage msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                GarageDoorMessage message = (GarageDoorMessage) msg;
                SmartDeviceNameLabel.setText(message.getName());
                if (message.getStatus()) {
                    StatusIndicatorLabel.setText("Open");
                } else {
                    StatusIndicatorLabel.setText("Closed");
                }


            }
        });

    }

    private void UpdateServer(){
        GarageDoorMessage msg = new GarageDoorMessage(true, deviceID, SmartDeviceNameLabel.getText(), true, 100, true, false, false, false, false, 0, 0 );
        client.UpdateServer(msg);
    }
}
