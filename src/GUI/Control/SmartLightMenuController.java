package GUI.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import smartDevice.SmartDevice;
import smartDevice.SmartLight;

public class SmartLightMenuController extends AbstractController{

    @FXML
    private Slider BrightnessSlider;

    @FXML
    private Button ChangeColourButton;

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
    private SmartLight light = new SmartLight(true, 69, true, "Default Light Name", 0xFFFFFF, 69, true, 1);

    public void setPreviousScene(Scene previousScene) {
        previous = previousScene;
    }

    public void BackButtonPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previous);

    }
    @Override
    public void link(Object l){
        light = (SmartLight) l;
        BrightnessSlider.adjustValue(light.getBrightness());
        SmartDeviceNameLabel.setText(light.getName());
        StatusIndicatorLabel.setText(light.getStatus() ? "On" : "Off");
        brightnessLabel.setText(light.getBrightness() + "%");
    }

    @Override
    public SmartDevice getSmartDevice() {return light; }

    public void ToggleLightStatusButtonPressed(ActionEvent actionEvent) {
        light.setStatus(!light.getStatus());
        StatusIndicatorLabel.setText(light.getStatus() ? "On" : "Off");
        System.out.println(light.getStatus());
        SmartDeviceImageView.setImage(light.getStatus() ? new javafx.scene.image.Image("/GUI/Images/light Icon.png") : new javafx.scene.image.Image("/GUI/Images/light_Icon_off.png"));
    }

    public void ChangeColourButtonPressed(ActionEvent actionEvent) {

    }

    public void CreateAutomationButtonPressed(ActionEvent actionEvent) {

    }

    public void EditAutomationsButtonPressed(ActionEvent actionEvent) {

    }


    public void BrightnessSliderReleased(MouseEvent mouseEvent) {
        light.setBrightness((int) BrightnessSlider.getValue());
        brightnessLabel.setText((int)light.getBrightness() + "%" );
    }


}
