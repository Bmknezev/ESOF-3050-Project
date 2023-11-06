package GUI.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import smartDevice.SmartLight;

public class SmartLightMenuController {

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

    private Scene first;

    private SmartLight light;

    public void setFirstScene(Scene firstScene) {
        first = firstScene;
    }

    public void BackButtonPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(first);

    }
    public void linkLight(SmartLight l){
        light = l;
        BrightnessSlider.adjustValue(light.getBrightness());
        SmartDeviceNameLabel.setText(light.getName());
        StatusIndicatorLabel.setText(light.getStatus() ? "On" : "Off");
        brightnessLabel.setText(String.valueOf(light.getBrightness()) + "%");
    }

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
        brightnessLabel.setText(String.valueOf(light.getBrightness()) + "%" );
    }
}
