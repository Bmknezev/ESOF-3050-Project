package GUI.Control;

import GUI.Control.Abstract.AbstractDeviceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DeviceSelectionMenuController extends AbstractDeviceController {

    @FXML
    private Button backButton;

    @FXML
    private VBox deviceVBox;

    private Scene previous;

    private Scene[] sceneList = new Scene[5];
    AbstractDeviceController[] Controller;


    public void setPreviousScene(Scene previousScene) {
        previous = previousScene;
    }

    public void addNewDevice(String newDevice) {
        //recieves a string from the server and parses it using delimiter character |, string contains device id, name, and device type
        String[] s = newDevice.split("\\|");
            // this creates a new label for the device name
        Label deviceNameLabel = new Label();
            // these set the parameters of the label
        System.out.println(s[0]);
        deviceNameLabel.setText(s[0]);
        deviceNameLabel.setWrapText(true);
        deviceNameLabel.setFont(Font.font("System", FontWeight.BOLD, 15));

            // this creates a new label that shows the type of the new device
        Label deviceTypeLabel = new Label();
            // these set the parameters of the label
        deviceTypeLabel.setText(s[1]);

            // this creates a new button that is linked to the new device
        Button manageDeviceButton = new Button("Manage Device");
            // this sets the button to change the scene when pressed
        manageDeviceButton.setOnAction(event ->{
            Stage stage = (Stage) manageDeviceButton.getScene().getWindow();
            //this switch statement changes the scene based on the device type and requests the values for that device from the server
            switch (s[1]) {
                case "Smart Light":
                    client.request(Integer.parseInt(s[2]), Controller[0]);
                    stage.setScene(sceneList[0]);
                    break;
                case "Smart Lock":
                    client.request(Integer.parseInt(s[2]), Controller[1]);
                    stage.setScene(sceneList[1]);
                    break;
                    case "Smart Thermostat":
                    client.request(Integer.parseInt(s[2]), Controller[2]);
                    stage.setScene(sceneList[2]);
                default:
                    break;
            }
        });

            // this creates a new hbox to contain the new elements created
        HBox deviceControlHBox = new HBox();
            // these set the parameters of the hbox
        deviceControlHBox.setSpacing(15);
        deviceControlHBox.setAlignment(Pos.CENTER_RIGHT);
            // this adds the elements to the hbox
        deviceControlHBox.getChildren().addAll(deviceTypeLabel, manageDeviceButton);
            // this adds the hbox to the vbox

        StackPane deviceStackPane = new StackPane();
        deviceStackPane.setAlignment(Pos.CENTER_LEFT);

        deviceStackPane.getChildren().addAll(deviceNameLabel, deviceControlHBox);

        deviceVBox.getChildren().add(deviceStackPane);
    }

    @FXML
    void backButtonPressed(ActionEvent event) {
            // these change the scene when the back button is pressed
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previous);
    }

    public void addScene(Scene[] scenelist, AbstractDeviceController[] controller) {
        //lists of all the device scenes and thier controllers
        sceneList = scenelist;
        Controller = controller;
    }

    @Override
    public void update(String[] s) {
        //dont worry about this, im doing bad programming practices but it works

    }

}
