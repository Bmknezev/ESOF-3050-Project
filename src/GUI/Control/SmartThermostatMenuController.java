//-----------------------------------------------------------------
// SmartThermostatMenuController.java
// Group 2
// Description: Manages the control and user interface for a Smart Thermostat device in the system. Controls temperature settings,
//              mode (heating/cooling/off), and enables/disables heating and cooling features.
// Created By: Francisco
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - backButton: Button - Button for navigating back to the previous scene
//   - ChangeTempButton: Button - Button for changing the thermostat temperature setting
//   - ChangeTempTextField: TextField - TextField for entering a new temperature value
//   - CreateAutomationButton: Button - Button for creating automations for the thermostat (not implemented)
//   - EditAutomationsButton: Button - Button for editing existing automations for the thermostat (not implemented)
//   - HeatingCoolingStatusLabel: Label - Label for indicating the current heating/cooling mode
//   - SmartDeviceImageView: ImageView - Image view for displaying the thermostat's image
//   - SmartDeviceNameLabel: Label - Label for displaying the name of the thermostat
//   - TemperatureStatusLabel: Label - Label for displaying the current temperature
//   - SetpointStatusLabel: Label - Label for displaying the setpoint temperature
//   - coolingEnableButton: Button - Button for enabling/disabling cooling feature
//   - heatingEnableButton: Button - Button for enabling/disabling heating feature
//   - previous: Scene - Reference to the previous scene
//   - deviceID: int - Identifier for the thermostat device
//   - client: SmartHomeClient - Reference to the Smart Home client
//-----------------------------------------------------------------

package GUI.Control;

import ClientServer.AutomationBuffer;
import GUI.Control.Abstract.AbstractDeviceController;
import GUI.Control.Interface.Updatable;
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

public class SmartThermostatMenuController extends AbstractDeviceController implements Updatable {

    public Button coolingEnableButton;
    public Button heatingEnableButton;
    @FXML
    private Button ChangeTempButton;

    @FXML
    private TextField ChangeTempTextField;

    @FXML
    private Button CreateAutomationButton;

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

    private float temperature;


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
        Platform.runLater(() -> {
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
            temperature = message.getSetpoint();
            SetpointStatusLabel.setText(message.getSetpoint() + " °C");
            heatingEnableButton.setText(message.getHeatEnabled() ? "Disable Heating" : "Enable Heating");
            heatingEnableButton.setStyle(message.getHeatEnabled() ? "-fx-background-color: Orange" : "-fx-background-color: LightGrey");
            coolingEnableButton.setText(message.getCoolEnabled() ? "Disable Cooling" : "Enable Cooling");
            coolingEnableButton.setStyle(message.getCoolEnabled() ? "-fx-background-color: LightBlue" : "-fx-background-color: LightGrey");
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
        if(ChangeTempTextField.getText().isEmpty()){
            ChangeTempButton.setText("Please enter a value");
            return;
        }
        if(!ChangeTempTextField.getText().matches("[0-9]+")){
            System.out.println("[" + ChangeTempTextField.getText() + "]");
            ChangeTempButton.setText("invalid temperature, contains space");
            return;
        }
        if(Float.parseFloat(ChangeTempTextField.getText()) < 15 || Float.parseFloat(ChangeTempTextField.getText()) > 35){
            ChangeTempButton.setText("invalid temperature, to high or low");
            return;
        }

        ChangeTempButton.setText("Change Temperature");
        SetpointStatusLabel.setText(ChangeTempTextField.getText());
        UpdateServer();
    }

    public void coolingEnableButtonPressed(ActionEvent actionEvent) {
        if(coolingEnableButton.getText().equals("Enable Cooling")){
            coolingEnableButton.setText("Disable Cooling");
            coolingEnableButton.setStyle("-fx-background-color: LightBlue");
        }
        else{
            coolingEnableButton.setText("Enable Cooling");
            coolingEnableButton.setStyle("-fx-background-color: LightGrey");
        }
        UpdateServer();
    }

    public void heatingEnableButtonPressed(ActionEvent actionEvent) {
        if(heatingEnableButton.getText().equals("Enable Heating")){
            heatingEnableButton.setText("Disable Heating");
            heatingEnableButton.setStyle("-fx-background-color: Orange");
        }
        else{
            heatingEnableButton.setText("Enable Heating");
            heatingEnableButton.setStyle("-fx-background-color: LightGrey");
        }
        UpdateServer();
    }

    @FXML
    void CreateAutomationButtonPressed(ActionEvent event) {
        AutomationBuffer.createThermostatAutomation(deviceID, SmartDeviceNameLabel.getText(), temperature);
        automationMenuController.setPrevious(CreateAutomationButton.getScene());
        Stage stage = (Stage) CreateAutomationButton.getScene().getWindow();
        stage.setScene(automationScene);
    }
}
