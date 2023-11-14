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
    public void update(String[] s) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                deviceID = Integer.parseInt(s[0]);
                SmartDeviceNameLabel.setText(s[1]);
                SmartDeviceImageView.setImage(Boolean.parseBoolean(s[2]) ? new javafx.scene.image.Image("/GUI/Images/Smoke Detector Icon.png") : new javafx.scene.image.Image("/GUI/Images/Smoke Detector Icon.png"));
                StatusIndicatorLabel.setText(Boolean.parseBoolean(s[2]) ? "On" : "Off");
                //TestStatusLabel.setText(Boolean.parseBoolean(s[3]) ? "Test Needed" : "Test Not Needed");
                //alarm is s[4]
                BatteryStatusLabel.setText(s[5] + "%");
                PreviousTestDateLabel.setText(s[6]);

            }
        });

    }

    private void UpdateServer(String msg){
        String message = 0 + "@" + deviceID + "@" + msg;
        try {
            super.client.sendToServer(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
