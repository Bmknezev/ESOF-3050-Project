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
import messages.server.LockMessage;

public class SmartLockMenuController extends AbstractDeviceController implements Updatable {

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
    private Button TempUnlockButton;

    @FXML
    private TextField TempUnlockTextField;

    @FXML
    private Button ToggleLockStatusButton;

    @FXML
    private Button backButton;

    private Scene previous;
    // this is just a default object to test the GUI

    private int deviceID;
    private TextInputDialog td = new TextInputDialog("");
    private int pin;







    public void setPreviousScene(Scene previousScene) {
        previous = previousScene;
    }
    public void BackButtonPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previous);
    }

    @Override
    public void update(AbstractDeviceMessage msg) {
        Platform.runLater(() -> {
            LockMessage message = (LockMessage) msg;
            SmartDeviceNameLabel.setText(message.getName());
            deviceID = message.getDeviceID();
            pin = message.getPIN();
            if(message.getLockStatus()){
                StatusIndicatorLabel.setText("Locked");
                SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/Lock Icon.png"));
                ToggleLockStatusButton.setText("Unlock");
            }
            else{
                StatusIndicatorLabel.setText("Unlocked");
                SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/Lock Icon.png"));
                ToggleLockStatusButton.setText("Lock");
            }

        });

    }

    @FXML
    void ChangePINButtonPressed(ActionEvent event) {
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

    @FXML
    void CreateAutomationButtonPressed(ActionEvent event) {

    }

    @FXML
    void EditAutomationButtonPressed(ActionEvent event) {

    }

    @FXML
    void TempUnlockButtonPressed(ActionEvent event) {

    }

    @FXML
    void ToggleLockStatusButtonPressed(ActionEvent event) {
        if (StatusIndicatorLabel.getText().equals("Locked")) {
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
            StatusIndicatorLabel.setText("Locked");
            SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/Lock Icon.png"));
            UpdateServer();
        }

    }

    @Override
    public void response(PinMessage msg) {
        System.out.println("PIN response received");
        Platform.runLater(() -> {
        if(msg.getPinStatus()){
            StatusIndicatorLabel.setText("Unlocked");
            SmartDeviceImageView.setImage(new javafx.scene.image.Image("/GUI/Images/Lock Icon.png"));
            UpdateServer();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incorrect PIN");
            alert.setHeaderText("Incorrect PIN");
            alert.setContentText("The PIN you entered was incorrect. Please try again.");
            alert.showAndWait();
        }
        });
    }

    @Override
    public void setPIN(int newPin) {
        pin = newPin;
    }

    private void UpdateServer(){
        LockMessage message = new LockMessage(deviceID,SmartDeviceNameLabel.getText(), StatusIndicatorLabel.getText().equals("Locked"), 0,  pin);
        client.UpdateServer(message);
    }

    @FXML
    void backButtonPressed(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previous);
    }
}
