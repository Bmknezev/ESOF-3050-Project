package GUI.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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

    private Scene first;

    public void setFirstScene(Scene firstScene) {
        first = firstScene;
    }

    public void BackButtonPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(first);

    }
}
