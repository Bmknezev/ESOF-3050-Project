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
import messages.ThermostatMessage;

public class SmartThermostatMenuController extends AbstractDeviceController {

    @FXML
    private Button ChangeTempButton;

    @FXML
    private TextField ChangeTempTextField;

    @FXML
    private Button CreateAutomationButton;

    @FXML
    private Button EditAutomationsButton;

    @FXML
    private Button backButton;

    @FXML
    private Label HeatingCoolingStatusLabel;

    @FXML
    private ImageView SmartDeviceImageView;

    @FXML
    private Label SmartDeviceNameLabel;

    @FXML
    private Label TemperatureStatusLabel;

    @FXML
    private Label SetpointStatusLabel;

    private Scene previous;
    // this is just a default object to test the GUI

    private int deviceID;


    // this is a method that sets the previous scene to the scene that was passed in
    public void setPreviousScene(Scene previousScene) {
        previous = previousScene;
    }

    //change scenes when back button pressed
    public void backButtonPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previous);

    }

    @Override
    public void update(AbstractDeviceMessage msg) {
        //take input string and update GUI
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ThermostatMessage message = (ThermostatMessage) msg;
                if(message.getMode()) {
                    HeatingCoolingStatusLabel.setText("Heating");
                }
                else {
                    HeatingCoolingStatusLabel.setText("Cooling");
                }
                SmartDeviceNameLabel.setText(message.getName());
                deviceID = message.getDeviceID();
                TemperatureStatusLabel.setText(message.getTemperature() + "°C");
                SetpointStatusLabel.setText(message.getSetpoint() + "°C");
            }
        });

    }

    //send new values to server
    private void UpdateServer(){
        ThermostatMessage message = new ThermostatMessage(true, deviceID, SmartDeviceNameLabel.getText(), true, 100, true, 20, 20, true, true,true );
        client.UpdateServer(message);
    }

    //change temperature when button pressed, request new values from server
    public void ChangeTempButtonPressed(ActionEvent actionEvent) {
        SetpointStatusLabel.setText(ChangeTempTextField.getText());
        UpdateServer();
        super.client.request(deviceID, this);
    }
}
