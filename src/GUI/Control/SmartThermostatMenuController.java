package GUI.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    public void update(String[] s) {

    }

}
