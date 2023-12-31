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
