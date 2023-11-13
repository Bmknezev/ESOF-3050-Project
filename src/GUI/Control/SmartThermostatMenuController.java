package GUI.Control;

import GUI.Control.Abstract.AbstractDeviceController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SmartThermostatMenuController extends AbstractDeviceController {

    @FXML
    private Button ChangeTempButton;

    @FXML
    private TextField ChangeTempTextField;

    @FXML
    private Button CreateAutomationButton;

    @FXML
    private Button EditAutomationsButton;

    @FXML
    private Button backButton;

    @FXML
    private Label HeatingCoolingStatusLabel;

    @FXML
    private ImageView SmartDeviceImageView;

    @FXML
    private Label SmartDeviceNameLabel;

    @FXML
    private Label TemperatureStatusLabel;

    @FXML
    private Label SetpointStatusLabel;

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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                deviceID = Integer.parseInt(s[0]);
                SmartDeviceNameLabel.setText(s[1]);
                SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/Thermostat Icon.png"));
                TemperatureStatusLabel.setText(s[2]);
                SetpointStatusLabel.setText(s[3]);
                HeatingCoolingStatusLabel.setText(s[4]);

            }
        });

    }

    public void backButtonPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previous);
    }

    private void UpdateServer(String msg){
        String message = 0 + "@" + deviceID + "@" + msg;
        try {
            super.client.sendToServer(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void ChangeTempButtonPressed(ActionEvent actionEvent) {
        SetpointStatusLabel.setText(ChangeTempTextField.getText());
        UpdateServer("setpoint|" + ChangeTempTextField.getText());

        super.client.request(deviceID, this);
    }
}
