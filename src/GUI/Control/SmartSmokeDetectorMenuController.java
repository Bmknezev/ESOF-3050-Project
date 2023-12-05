//-----------------------------------------------------------------
// SmartSmokeDetectorMenuController.java
// Group 2
// Description: Manages the control and user interface for the Smart Smoke Detector device in the system. Controls smoke detection status,
//              battery status, test alarm, and handles user input.
// Created By: Francisco
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - backButton: Button - Button for navigating back to the previous scene
//   - batteryStatusLabel: Label - Label for displaying the battery status of the smoke detector
//   - createAutomationButton: Button - Button for creating automations for the smoke detector (not implemented)
//   - editAutomationsButton: Button - Button for editing existing automations for the smoke detector (not implemented)
//   - previousTestDateLabel: Label - Label for displaying the date of the last smoke detector test
//   - smartDeviceImageView: ImageView - Image view for displaying the smoke detector's image
//   - smartDeviceNameLabel: Label - Label for displaying the name of the smoke detector
//   - statusIndicatorLabel: Label - Label for indicating the smoke detection status (Active / Inactive)
//   - testAlarmButton: Button - Button for testing the smoke detector alarm
//   - previous: Scene - Reference to the previous scene
//   - deviceID: int - Identifier for the smoke detector device
//   - client: SmartHomeClient - Reference to the Smart Home client
//-----------------------------------------------------------------

package GUI.Control;

import GUI.Control.Abstract.AbstractDeviceController;
import GUI.Control.Interface.Updatable;
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

public class SmartSmokeDetectorMenuController extends AbstractDeviceController implements Updatable {

    @FXML
    private Button backButton;

    @FXML
    private Label batteryStatusLabel;

    @FXML
    private Button createAutomationButton;

    @FXML
    private Button editAutomationsButton;

    @FXML
    private Label previousTestDateLabel;

    @FXML
    private ImageView smartDeviceImageView;

    @FXML
    private Label smartDeviceNameLabel;

    @FXML
    private Label statusIndicatorLabel;

    @FXML
    private Button testAlarmButton;

    @FXML
    void backButtonPressed(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previous);
    }

    @FXML
    void testAlarmButtonPressed(ActionEvent event) {
        SmokeDetectorMessage alarm = new SmokeDetectorMessage(
                deviceID, smartDeviceNameLabel.getText(), new Date(), false, true
        );
        client.UpdateServer(alarm);
    }

    private Scene previous;
    // this is just a default object to test the GUI

    private int deviceID;


    public void setPreviousScene(Scene previousScene) {
        previous = previousScene;
    }

    @Override
    public void update(AbstractDeviceMessage msg) {
        Platform.runLater(() -> {
            SmokeDetectorMessage message = (SmokeDetectorMessage) msg;
            smartDeviceNameLabel.setText(message.getName());
            deviceID = message.getDeviceID();
            if (message.getAlarmStatus()) {
                statusIndicatorLabel.setText("Active");
            } else {
                statusIndicatorLabel.setText("Inactive");
            }
            if (message.getLastTested() == null)
                previousTestDateLabel.setText("Never Tested");
            else
                previousTestDateLabel.setText(message.getLastTested().toString());
        });

    }

    private void UpdateServer(){

    }
}
