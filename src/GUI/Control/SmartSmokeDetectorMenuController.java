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
