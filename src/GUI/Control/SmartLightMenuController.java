//-----------------------------------------------------------------
// SmartLightMenuController.java
// Group 2
// Description: Manages the control and user interface for the Smart Light device in the system. Controls light status (on/off),
//              brightness, color, and handles user input.
// Created By: Francisco
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - lightColour: Pane - Pane representing the color of the light
//   - colourPicker: ColorPicker - ColorPicker for selecting light color
//   - BrightnessSlider: Slider - Slider for adjusting brightness of the light
//   - CreateAutomationButton: Button - Button for creating automations for the smart light
//   - EditAutomationsButton: Button - Button for editing existing automations for the smart light
//   - SmartDeviceImageView: ImageView - Image view for displaying the smart light's image
//   - SmartDeviceNameLabel: Label - Label for displaying the name of the smart light
//   - StatusIndicatorLabel: Label - Label for indicating the status of the light (On / Off)
//   - ToggleLightStatusButton: Button - Button for toggling the light status (On / Off)
//   - backButton: Button - Button for navigating back to the previous scene
//   - brightnessLabel: Label - Label for displaying the brightness level of the light
//   - previous: Scene - Reference to the previous scene
//   - deviceID: int - Identifier for the smart light device
//   - td: TextInputDialog - Dialog for user input (changing PIN, entering PIN)
//   - client: SmartHomeClient - Reference to the Smart Home client
//-----------------------------------------------------------------

package GUI.Control;

import ClientServer.AutomationBuffer;
import ClientServer.SmartDeviceIndex;
import GUI.Control.Abstract.AbstractDeviceController;
import GUI.Control.Interface.Updatable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import messages.AbstractDeviceMessage;
import messages.automations.LightAutomationMessage;
import messages.server.LightMessage;

import java.util.Date;
import java.util.Objects;


public class SmartLightMenuController extends AbstractDeviceController implements Updatable {

    public Pane lightColour;
    @FXML
    public ColorPicker colourPicker;
    @FXML
    private Slider BrightnessSlider;

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
    private Button ToggleLightStatusButton;

    @FXML
    private Button backButton;

    @FXML
    private Label brightnessLabel;

    private Scene previous;
        // this is just a default object to test the GUI

    private int deviceID;
    private String colour = "ffff00";


    public void setPreviousScene(Scene previousScene) {
        previous = previousScene;
    }

    public void BackButtonPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previous);
    }

    @Override
    public void update(AbstractDeviceMessage msg) {
        //this method updates the GUI with the values from the server
        //this is run on the JavaFX thread
        Platform.runLater(() -> {
            //convert the message to a LightMessage
            LightMessage message = (LightMessage) msg;
            //name and device id are always included in the message
            SmartDeviceNameLabel.setText(message.getName());
            deviceID = message.getDeviceID();

            //check if values are updated, if they are update the GUI
            if(message.getLightStatus()){
                StatusIndicatorLabel.setText("On");
                ToggleLightStatusButton.setText("Turn Off");
                lightColour.setVisible(true);
            }
            else{
                StatusIndicatorLabel.setText("Off");
                ToggleLightStatusButton.setText("Turn On");
                lightColour.setVisible(false);
            }
            //setting the light icon
            SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/light_Icon_off.png"));
            //setting the light colour
            lightColour.setStyle("-fx-background-color: #" + message.getColour());
            //setting the brightness slider
            BrightnessSlider.setValue(message.getBrightness());
            //setting the brightness label
            brightnessLabel.setText(message.getBrightness() + "%");
            //setting the colour picker
            colourPicker.setValue(javafx.scene.paint.Color.valueOf(message.getColour()));

        });

    }

    public void ToggleLightStatusButtonPressed(ActionEvent actionEvent) {
        //runs when the on/off button is pressed

        //first checks if the light is on or off and changes the GUI accordingly
        if(Objects.equals(StatusIndicatorLabel.getText(), "On")){
            //change labels
            StatusIndicatorLabel.setText("Off");
            //change text on on/off button
            ToggleLightStatusButton.setText("Turn On");
            //sets the colour pane to be invisible
            lightColour.setVisible(false);
        }
        else{
            //change labels
            StatusIndicatorLabel.setText("On");
            //change text on on/off button
            ToggleLightStatusButton.setText("Turn Off");
            //sets the colour pane to be visible
            lightColour.setVisible(true);
        }
        //updates the server with the new values
    UpdateServer();
    }

    public void CreateAutomationButtonPressed(ActionEvent actionEvent) {
        AutomationBuffer.createLightAutomation(deviceID, SmartDeviceNameLabel.getText(), colour, (int) BrightnessSlider.getValue(), getLightStatus());
        automationMenuController.setPrevious(this.getScene());
        Stage stage = (Stage) CreateAutomationButton.getScene().getWindow();
        stage.setScene(automationScene);
    }

    public void BrightnessSliderDragged(MouseEvent mouseEvent) {
        //change text on the brightness label to match the slider
        brightnessLabel.setText((int) BrightnessSlider.getValue() + "%");
    }

    private void UpdateServer(){
        //this method updates the server with the new values

        //converts the on/off label to a boolean
        boolean lightStatus = Objects.equals(StatusIndicatorLabel.getText(), "On");
        //creates a new LightMessage with the new values
        LightMessage message = new LightMessage(deviceID, SmartDeviceNameLabel.getText(), lightColour.getStyle().substring(23), (int) BrightnessSlider.getValue(), lightStatus);
        //passes the message to the client to be sent to the server
        client.UpdateServer(message);
    }

    public void changeColour(ActionEvent actionEvent) {
        //Integer colourInt = colourPicker.getValue().hashCode(), tempInt;
        colour = String.format("%08x", colourPicker.getValue().hashCode());

        //System.out.println("New Colour Is: #" + colour);
            //uses the built in colour picker to change the colour of the light
        lightColour.setStyle("-fx-background-color: #" + colour);
        //updates the server with the new values
        UpdateServer();
    }

    public void sendBrightnessUpdate(MouseEvent mouseEvent) {
        //System.out.println("Brightness Update Sent");
        //updates the server with the new values
        UpdateServer();
    }

    @Override
    public String getDeviceName() {
        return SmartDeviceNameLabel.getText();
    }
    @Override
    public String getDeviceType() {
        return SmartDeviceIndex.getDeviceType(0);
    }
    @Override
    public int getDeviceID() {
        return deviceID;
    }
    @Override
    public Scene getScene(){
        return SmartDeviceNameLabel.getScene();
    }

    public boolean getLightStatus(){
        if(Objects.equals(StatusIndicatorLabel.getText(), "On")){
            return true;
        }
        else{
            return false;
        }
    }
}
