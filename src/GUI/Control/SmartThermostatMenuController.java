package GUI.Control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class SmartThermostatMenuController extends AbstractController{

    @FXML
    private Button ChangeTempButton;

    @FXML
    private TextField ChangeTempTextField;

    @FXML
    private Button CreateAutomationButton;

    @FXML
    private Button EditAutomationsButton;

    @FXML
    private Label HeatingCoolingStatusLabel;

    @FXML
    private ImageView SmartDeviceImageView;

    @FXML
    private Label SmartDeviceNameLabel;

    @FXML
    private Label TemperatureStatusLabel;

    @Override
    public void update(String[] s) {

    }
}
