package GUI.Control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class SmartGarageDoorOpenerMenuController extends AbstractController{

    @FXML
    private Button ChangePINButton;

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
    private Button TempOpenButton;

    @FXML
    private TextField TempOpenTextField;

    @FXML
    private Button ToggleGarageDoorStatusButton;

    @Override
    public void update(String[] s) {

    }


}
