package GUI.Control;

import GUI.Control.AbstractController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SmartSmokeDetectorMenuController extends AbstractController {

    @FXML
    private Label BatteryStatusLabel;

    @FXML
    private Button CreateAutomationButton;

    @FXML
    private Button EditAutomationsButton;

    @FXML
    private Label PreviousTestDateLabel;

    @FXML
    private ImageView SmartDeviceImageView;

    @FXML
    private Label SmartDeviceNameLabel;

    @FXML
    private Label StatusIndicatorLabel;

    @FXML
    private Button TestAlarmButton;

    @FXML
    private Button backButton;

    @FXML
    void backButtonPressed(ActionEvent event) {

    }

    @Override
    public void update(String[] s) {

    }
}
