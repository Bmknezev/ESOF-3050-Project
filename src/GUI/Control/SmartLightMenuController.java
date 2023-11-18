package GUI.Control;

import GUI.Control.Abstract.AbstractDeviceController;
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


public class SmartLightMenuController extends AbstractDeviceController {

    public Pane lightColour;
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
        Date date = new Date();
        date.setTime(date.getTime() + 10000);
        LightAutomationMessage message = new LightAutomationMessage(deviceID, lightColour.getStyle().substring(23), (int) BrightnessSlider.getValue(), true, date);
        client.UpdateServer(message);
    }

    public void EditAutomationsButtonPressed(ActionEvent actionEvent) {

    }


    public void BrightnessSliderReleased(MouseEvent mouseEvent) {
        //change text on the brightness label to match the slider
        brightnessLabel.setText((int) BrightnessSlider.getValue() + "%");
        //updates the server with the new values
        //needs to be moved to a separate method
        UpdateServer();
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
        //uses the built in colour picker to change the colour of the light
        lightColour.setStyle("-fx-background-color: #" + Integer.toHexString(colourPicker.getValue().hashCode()));
        //updates the server with the new values
        UpdateServer();
    }
}
