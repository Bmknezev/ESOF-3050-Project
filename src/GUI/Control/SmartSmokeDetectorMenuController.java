package GUI.Control;

import GUI.Control.Abstract.AbstractDeviceController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import messages.AbstractDeviceMessage;
import messages.server.SmokeDetectorMessage;

import java.util.Date;

public class SmartSmokeDetectorMenuController extends AbstractDeviceController {

    @FXML
    private Label BatteryStatusLabel;

    @FXML
    private Button CreateAutomationButton;

    @FXML
    private Button EditAutomationsButton;

    @FXML
    private Label PreviousTestDateLabel;

    @FXML
    private ImageView SmartDeviceImageView;

    @FXML
    private Label SmartDeviceNameLabel;

    @FXML
    private Label StatusIndicatorLabel;

    @FXML
    private Button TestAlarmButton;

    @FXML
    private Button backButton;

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
                SmokeDetectorMessage message = (SmokeDetectorMessage) msg;
                SmartDeviceNameLabel.setText(message.getName());
                deviceID = message.getDeviceID();
                if (message.getAlarmStatus()) {
                    StatusIndicatorLabel.setText("Active");
                } else {
                    StatusIndicatorLabel.setText("Inactive");
                }
                //PreviousTestDateLabel.setText(message.getPreviousTestDate());


            }
        });

    }

    private void UpdateServer(){

    }
}
