package GUI.Control;

import GUI.Control.Abstract.AbstractDeviceController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SmartCoffeeMakerMenuController extends AbstractDeviceController {

    @FXML
    private Button BrewCoffeeButton;

    @FXML
    private Button CreateAutomationButton;

    @FXML
    private Button EditAutomationsButton;

    @FXML
    private Button backButton;

    @FXML
    private ToggleGroup Size;

    @FXML
    private ImageView SmartDeviceImageView;

    @FXML
    private Label SmartDeviceNameLabel;

    @FXML
    private Label StatusIndicatorLabel;

    @FXML
    private ToggleGroup Strength;

    @FXML
    private ToggleGroup Temperature;

    private Scene previous;
    // this is just a default object to test the GUI

    private int deviceID;


    public void setPreviousScene(Scene previousScene) {
        previous = previousScene;
    }

    @FXML
    public void backButtonPressed(ActionEvent actionEvent) {
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
                //SmartDeviceImageView.setImage(Boolean.parseBoolean(s[2]) ? new javafx.scene.image.Image("/GUI/Images/Coffee Icon.png") : new javafx.scene.image.Image("/GUI/Images/Coffee Icon.png"));
                StatusIndicatorLabel.setText(Boolean.parseBoolean(s[2]) ? "On" : "Off");
                //waterlevel
                //coffelevel
                //coffeetype
                //ready to brew
                //brewing


            }
        });

    }

    private void UpdateServer(String msg){
        String message = 0 + "@" + deviceID + "@" + msg;
        try {
            super.client.sendToServer(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
