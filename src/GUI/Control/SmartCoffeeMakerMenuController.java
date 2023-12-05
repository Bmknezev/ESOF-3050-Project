//-----------------------------------------------------------------
// SmartCoffeeMakerMenuController.java
// Group 2
// Description: This class manages the control and user interface for the Smart Coffee Maker device in the system. It handles
//              brewing coffee, displaying device status, and updating device parameters like water, coffee, and bean levels.
// Created By: Francisco
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - waterLevel: ProgressBar - Represents the water level in the coffee maker
//   - coffeeLevel: ProgressBar - Represents the coffee level in the coffee maker
//   - coffeeBeanLevel: ProgressBar - Represents the coffee bean level in the coffee maker
//   - smallButton: RadioButton - Radio button for selecting a small size for brewing coffee
//   - mediumButton: RadioButton - Radio button for selecting a medium size for brewing coffee
//   - largeButton: RadioButton - Radio button for selecting a large size for brewing coffee
//   - extraLargeButton: RadioButton - Radio button for selecting an extra-large size for brewing coffee
//   - BrewCoffeeButton: Button - Button for initiating the coffee brewing process
//   - CreateAutomationButton: Button - Button for creating automations for the coffee maker
//   - EditAutomationsButton: Button - Button for editing existing automations for the coffee maker
//   - backButton: Button - Button for navigating back to the previous scene
//   - Size: ToggleGroup - Toggle group for selecting coffee size options
//   - SmartDeviceImageView: ImageView - Image view for displaying the smart coffee maker's image
//   - SmartDeviceNameLabel: Label - Label for displaying the name of the smart coffee maker
//   - StatusIndicatorLabel: Label - Label for indicating the status of the smart coffee maker (Ready to Brew / Not Ready to Brew)
//   - Strength: ToggleGroup - Toggle group for selecting coffee strength options
//   - Temperature: ToggleGroup - Toggle group for selecting temperature options for brewing coffee
//   - previous: Scene - Reference to the previous scene
//   - deviceID: int - Identifier for the smart coffee maker device
//   - client: SmartHomeClient - Reference to the Smart Home client
//-----------------------------------------------------------------

package GUI.Control;

import GUI.Control.Abstract.AbstractDeviceController;
import GUI.Control.Interface.Updatable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import messages.AbstractDeviceMessage;
import messages.BrewCoffeeMessage;
import messages.server.CoffeeMessage;

import java.io.IOException;

public class SmartCoffeeMakerMenuController extends AbstractDeviceController implements Updatable {

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
        Platform.runLater(() -> {
            deviceID = msg.getDeviceID();
            CoffeeMessage message = (CoffeeMessage) msg;
            SmartDeviceNameLabel.setText(message.getName());
            StatusIndicatorLabel.setText(message.getReadyToBrew() ? "Ready to Brew" : "Not Ready to Brew");
            waterLevel.setProgress(message.getWaterLevel());
            coffeeLevel.setProgress(message.getCoffeeLevel());
            coffeeLevel.setStyle("-fx-accent: rgb(77,44,1)");
            coffeeBeanLevel.setProgress(message.getCoffeeBeanLevel());
            coffeeBeanLevel.setStyle("-fx-accent: rgb(70,44,11)");
        });

    }

    private void UpdateServer(){
        boolean ready = StatusIndicatorLabel.getText().equals("Ready to Brew");
        CoffeeMessage msg = new CoffeeMessage(deviceID, SmartDeviceNameLabel.getText(), false, waterLevel.getProgress(), coffeeBeanLevel.getProgress(), "Coffee", ready, 0.5);
        client.UpdateServer(msg);
    }


    public void BrewCoffeeButtonPressed(ActionEvent actionEvent) {

        try {
            client.sendToServer(new BrewCoffeeMessage(SmartDeviceNameLabel.getText(), deviceID, ((ToggleButton) Size.getSelectedToggle()).getText(), ((ToggleButton) Strength.getSelectedToggle()).getText(), waterLevel.getProgress(), coffeeBeanLevel.getProgress(), coffeeLevel.getProgress(), ((ToggleButton) Temperature.getSelectedToggle()).getText()));
        }catch (IOException e){
            System.out.println("Error sending message to server.");
        }
    }
}
