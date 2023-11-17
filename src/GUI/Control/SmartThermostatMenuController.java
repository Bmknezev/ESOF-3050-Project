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
import messages.server.ThermostatMessage;

public class SmartThermostatMenuController extends AbstractDeviceController {

    public Button coolingEnableButton;
    public Button heatingEnableButton;
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
                switch(((ThermostatMessage) msg).getMode()){
                    case 0:
                        HeatingCoolingStatusLabel.setText("Off");
                        break;
                    case 1:
                        HeatingCoolingStatusLabel.setText("Heating");
                        break;
                    case 2:
                        HeatingCoolingStatusLabel.setText("Cooling");
                        break;

                }
                SmartDeviceNameLabel.setText(message.getName());
                deviceID = message.getDeviceID();
                TemperatureStatusLabel.setText(message.getTemperature() + " °C");
                SetpointStatusLabel.setText(message.getSetpoint() + " °C");
                heatingEnableButton.setText(message.getHeatEnabled() ? "Disable Heating" : "Enable Heating");
                coolingEnableButton.setText(message.getCoolEnabled() ? "Disable Cooling" : "Enable Cooling");

            }
        });

    }

    //send new values to server
    private void UpdateServer(){
        boolean heat = heatingEnableButton.getText().equals("Disable Heating");
        boolean cool = coolingEnableButton.getText().equals("Disable Cooling");
        String setpoint = SetpointStatusLabel.getText();
        String[] setpointSplit = setpoint.split(" ");
        ThermostatMessage message = new ThermostatMessage(deviceID, SmartDeviceNameLabel.getText(), Float.parseFloat(setpointSplit[0]), heat, cool);
        client.UpdateServer(message);
    }

    //change temperature when button pressed, request new values from server
    public void ChangeTempButtonPressed(ActionEvent actionEvent) {
        SetpointStatusLabel.setText(ChangeTempTextField.getText());
        UpdateServer();
    }

    public void coolingEnableButtonPressed(ActionEvent actionEvent) {
        if(coolingEnableButton.getText().equals("Enable Cooling")){
            coolingEnableButton.setText("Disable Cooling");
        }
        else{
            coolingEnableButton.setText("Enable Cooling");
        }
        UpdateServer();
    }

    public void heatingEnableButtonPressed(ActionEvent actionEvent) {
        if(heatingEnableButton.getText().equals("Enable Heating")){
            heatingEnableButton.setText("Disable Heating");
        }
        else{
            heatingEnableButton.setText("Enable Heating");
        }
        UpdateServer();
    }
}
