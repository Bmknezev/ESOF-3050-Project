package GUI.Control;

import GUI.Control.Abstract.AbstractDeviceController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import messages.AbstractDeviceMessage;
import messages.server.CoffeeMessage;

import static java.lang.Thread.sleep;

public class SmartCoffeeMakerMenuController extends AbstractDeviceController {

    public ProgressBar waterLevel;
    public ProgressBar coffeeLevel;
    public ProgressBar coffeeBeanLevel;
    public RadioButton smallButton;
    public RadioButton mediumButton;
    public RadioButton largeButton;
    public RadioButton extraLargeButton;
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
    public void update(AbstractDeviceMessage msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                CoffeeMessage message = (CoffeeMessage) msg;
                SmartDeviceNameLabel.setText(message.getName());
                StatusIndicatorLabel.setText(message.getReadyToBrew() ? "Ready to Brew" : "Not Ready to Brew");
                waterLevel.setProgress(message.getWaterLevel());
                coffeeLevel.setProgress(message.getCoffeeLevel());
                coffeeBeanLevel.setProgress(message.getCoffeeBeanLevel());
            }
        });

    }

    private void UpdateServer(){
        CoffeeMessage msg = new CoffeeMessage(deviceID, SmartDeviceNameLabel.getText(), false, 0.5, 0.5, "Coffee", false, 0.5);
        client.UpdateServer(msg);
    }


    public void BrewCoffeeButtonPressed(ActionEvent actionEvent) {
        //pdateServer("BrewCoffee|"+((RadioButton)Size.getSelectedToggle()).getText()+"|"+((RadioButton)Strength.getSelectedToggle()).getText());
    }
}
