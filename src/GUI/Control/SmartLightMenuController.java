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
import java.util.Objects;

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

    private Scene first;
    private int deviceID;

    public void setFirstScene(Scene firstScene) {
        first = firstScene;
    }

    public void BackButtonPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(first);

    }

    @Override
    public void update(String[] s) {
        deviceID = Integer.parseInt(s[0]);
        SmartDeviceNameLabel.setText(s[1]);
        SmartDeviceImageView.setImage(Boolean.parseBoolean(s[2]) ? new javafx.scene.image.Image("/GUI/Images/light Icon.png") : new javafx.scene.image.Image("/GUI/Images/light_Icon_off.png"));
        StatusIndicatorLabel.setText(Boolean.parseBoolean(s[2]) ? "On" : "Off");
        brightnessLabel.setText(s[3] + "%");
        BrightnessSlider.setValue(Integer.parseInt(s[3]));
    }

    public void ToggleLightStatusButtonPressed(ActionEvent actionEvent) {
        if(Objects.equals(StatusIndicatorLabel.getText(), "On")){
            StatusIndicatorLabel.setText("Off");
            SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/light_Icon_off.png"));
        }
        else{
            StatusIndicatorLabel.setText("On");
            SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/light Icon.png"));
        }

        UpdateServer();
    }

    public void ChangeColourButtonPressed(ActionEvent actionEvent) {

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
        boolean tmp = StatusIndicatorLabel.getText().equals("On");
        String message = 0 + "@" + deviceID + "@" + tmp + "|" + (int)BrightnessSlider.getValue() + "|" + 0x000000;
        try {
            super.client.sendToServer(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
