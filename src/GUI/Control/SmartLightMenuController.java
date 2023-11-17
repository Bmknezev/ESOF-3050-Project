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
import messages.server.LightMessage;

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
        System.out.println("got details");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                LightMessage message = (LightMessage) msg;
                SmartDeviceNameLabel.setText(message.getName());
                deviceID = message.getDeviceID();
                if(message.getLightStatus()){
                    StatusIndicatorLabel.setText("On");
                    //SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/light Icon.png"));
                    ToggleLightStatusButton.setText("Turn Off");
                }
                else{
                    StatusIndicatorLabel.setText("Off");
                    ToggleLightStatusButton.setText("Turn On");
                }
                SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/light_Icon_off.png"));
                lightColour.setStyle("-fx-background-color: #" + message.getColour());
                System.out.println("colour: " + message.getColour());
                BrightnessSlider.setValue(message.getBrightness());
                brightnessLabel.setText(message.getBrightness() + "%");

            }
        });

    }



    public void ToggleLightStatusButtonPressed(ActionEvent actionEvent) {
        if(Objects.equals(StatusIndicatorLabel.getText(), "On")){
            StatusIndicatorLabel.setText("Off");
            ToggleLightStatusButton.setText("Turn On");
            lightColour.setVisible(false);
            //SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/light_Icon_off.png"));
        }
        else{
            StatusIndicatorLabel.setText("On");
            ToggleLightStatusButton.setText("Turn Off");
            lightColour.setVisible(true);
            //SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/light Icon.png"));
        }
    UpdateServer();
    }

    public void CreateAutomationButtonPressed(ActionEvent actionEvent) {

    }

    public void EditAutomationsButtonPressed(ActionEvent actionEvent) {

    }


    public void BrightnessSliderReleased(MouseEvent mouseEvent) {

        brightnessLabel.setText((int) BrightnessSlider.getValue() + "%");
        UpdateServer();
    }

    private void UpdateServer(){
        boolean lightStatus = Objects.equals(StatusIndicatorLabel.getText(), "On");
        //int color = Integer.parseInt(Integer.toHexString(Integer.parseInt(lightColour.getStyle().substring(23), 16)));
        System.out.println("colour: " + lightColour.getStyle());
        LightMessage message = new LightMessage(deviceID, SmartDeviceNameLabel.getText(), lightColour.getStyle().substring(23), (int) BrightnessSlider.getValue(), lightStatus);
        client.UpdateServer(message);
    }

    public void changeColourButtonPressed(ActionEvent actionEvent) {

    }

    public void changeColour(ActionEvent actionEvent) {
        lightColour.setStyle("-fx-background-color: #" + Integer.toHexString(colourPicker.getValue().hashCode()));
        UpdateServer();
    }
}
