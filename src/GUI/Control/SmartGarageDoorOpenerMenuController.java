//-----------------------------------------------------------------
// SmartGarageDoorOpenerMenuController.java
// Group 2
// Description: This class manages the control and user interface for the Smart Garage Door Opener device in the system. It
//              controls garage door status (open/close), changing PIN, and handles user input.
// Created By:
// Edited By:
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - ChangePINButton: Button - Button for changing the PIN of the garage door opener
//   - CreateAutomationButton: Button - Button for creating automations for the garage door opener
//   - EditAutomationsButton: Button - Button for editing existing automations for the garage door opener
//   - backButton: Button - Button for navigating back to the previous scene
//   - SmartDeviceImageView: ImageView - Image view for displaying the smart garage door opener's image
//   - SmartDeviceNameLabel: Label - Label for displaying the name of the smart garage door opener
//   - StatusIndicatorLabel: Label - Label for indicating the status of the garage door (Open / Closed)
//   - TempOpenButton: Button - Button for temporarily opening the garage door
//   - TempOpenTextField: TextField - Text field for inputting time for temporary opening
//   - ToggleGarageDoorStatusButton: Button - Button for toggling the garage door status (Open / Close)
//   - previous: Scene - Reference to the previous scene
//   - deviceID: int - Identifier for the smart garage door opener device
//   - td: TextInputDialog - Dialog for user input (changing PIN, entering PIN)
//   - pin: int - PIN for the garage door opener
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import messages.AbstractDeviceMessage;
import messages.PinMessage;
import messages.server.GarageDoorMessage;

public class SmartGarageDoorOpenerMenuController extends AbstractDeviceController implements Updatable {

    @FXML
    private Button ChangePINButton;

    @FXML
    private Button CreateAutomationButton;

    @FXML
    private Button EditAutomationsButton;

    @FXML
    private Button backButton;

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

    private Scene previous;
    // this is just a default object to test the GUI

    private int deviceID;

    private TextInputDialog td = new TextInputDialog("");

    private int pin;


    public void setPreviousScene(Scene previousScene) {
        previous = previousScene;
    }

    public void backButtonPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previous);

    }

    @Override
    public void update(AbstractDeviceMessage msg) {
        Platform.runLater(() -> {
            GarageDoorMessage message = (GarageDoorMessage) msg;
            SmartDeviceNameLabel.setText(message.getName());
            System.out.println(message.getDoorStatus());
            if (message.getDoorStatus()) {
                StatusIndicatorLabel.setText("Open");
            } else {
                StatusIndicatorLabel.setText("Closed");
            }
            deviceID = message.getDeviceID();
            pin = message.getPIN();


        });

    }

    private void UpdateServer(){
        boolean status = StatusIndicatorLabel.getText().equals("Open");
        GarageDoorMessage msg = new GarageDoorMessage(deviceID, SmartDeviceNameLabel.getText(), status, true, pin);
        client.UpdateServer(msg);
    }

    public void ToggleGarageDoorButtonPressed(ActionEvent actionEvent) {
        if (StatusIndicatorLabel.getText().equals("Closed")) {
            td.setHeaderText("Enter PIN");
            td.setTitle("Enter PIN");
            td.getEditor().setText("");
            DialogPane dialogPane = td.getDialogPane();
            Label pinPrompt = new Label("Enter PIN");
            TextField pinInput = new TextField();
            dialogPane.setContent(new VBox(8, new HBox(8, pinPrompt, pinInput)));

            td.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK && !pinInput.getText().isEmpty()) {
                    client.checkPIN(new PinMessage(deviceID, Integer.parseInt(pinInput.getText()), -1, true), this);
                }
                return null;
            });

            td.showAndWait();


        } else {
            StatusIndicatorLabel.setText("Closed");
            SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/Garage Door Opener Icon.png"));
            UpdateServer();
        }
    }

    @Override
    public void response(PinMessage msg) {
        Platform.runLater(() -> {
            if (msg.getPinStatus()) {
                StatusIndicatorLabel.setText("Open");
                SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/Garage Door Opener Icon.png"));
                UpdateServer();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("PIN Error");
                alert.setHeaderText("Incorrect PIN");
                alert.setContentText("Please try again.");
                alert.showAndWait();
            }
        });
    }

    public void ChangePINButtonPressed(ActionEvent actionEvent) {
        DialogPane dialogPane = td.getDialogPane();
        Label pinPrompt = new Label("Enter current PIN");
        TextField pinInput = new TextField();
        Label pinPrompt2 = new Label("Enter new PIN");
        TextField pinInput2 = new TextField();
        dialogPane.setContent(new VBox(8, new HBox(8, pinPrompt, pinInput), new HBox(8, pinPrompt2, pinInput2)));
        td.setHeaderText("Change PIN");
        td.setTitle("Change PIN");
        td.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK && !pinInput.getText().isEmpty() && !pinInput2.getText().isEmpty()) {
                client.checkPIN(new PinMessage (deviceID, Integer.parseInt(pinInput.getText()), Integer.parseInt(pinInput2.getText()), false), this);
            }
            return null;
        });

        td.showAndWait();
    }

    @Override
    public void setPIN(int newPin) {
        pin = newPin;
    }
}
