package GUI.Control;

import ClientServer.SmartHomeClient;
import GUI.Control.Abstract.AbstractDeviceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import messages.AbstractDeviceMessage;
import messages.NewDeviceMessage;
import messages.StartupMessage;
import messages.UserListMessage;

public class DeviceSelectionMenuController extends AbstractDeviceController {


    @FXML
    private Button backButton;

    @FXML
    private Label welcomeUserLabel;

    @FXML
    private Label elementListIndicatorLabel;

    @FXML
    private VBox deviceVBox;

    private Scene previous;

    @FXML
    MenuButton settingsMenuButton = new MenuButton();

    @FXML
    MenuItem toggleUserListMenuItem = new MenuItem();
    @FXML
    MenuItem addNewElementMenuItem = new MenuItem();
    @FXML
    MenuItem deleteElementMenuItem = new MenuItem();
    @FXML
    public MenuItem promoteUserMenuItem;
    @FXML
    public MenuItem demoteUserMenuItem;

    private Scene[] sceneList = new Scene[5];
    private AbstractDeviceController[] Controller;

    private boolean userListActive = false;

    public void setPreviousScene(Scene previousScene) {
        previous = previousScene;
    }

    public void setClient(SmartHomeClient client) {
        this.client = client;
    }

    public void addNewDevice(NewDeviceMessage newDevice) {
            // this creates a new label for the device name
        Label deviceNameLabel = new Label();
            // these set the parameters of the label
        deviceNameLabel.setText(newDevice.getDeviceName());
        deviceNameLabel.setWrapText(true);
        deviceNameLabel.setFont(Font.font("System", FontWeight.BOLD, 15));

            // this creates a new label that shows the type of the new device
        Label deviceTypeLabel = new Label();
            // these set the parameters of the label
        deviceTypeLabel.setText(newDevice.getDeviceType());

            // this creates a new button that is linked to the new device
        Button manageDeviceButton = new Button("Manage Device");
            // this sets the button to change the scene when pressed
        manageDeviceButton.setOnAction(event ->{
            Stage stage = (Stage) manageDeviceButton.getScene().getWindow();
            //this switch statement changes the scene based on the device type and requests the values for that device from the server
            switch (newDevice.getDeviceType()) {
                case "Smart Light":
                    client.request(newDevice.getDeviceID(), Controller[0]);
                    stage.setScene(sceneList[0]);
                    break;
                case "Smart Lock":
                    client.request(newDevice.getDeviceID(), Controller[1]);
                    stage.setScene(sceneList[1]);
                    break;
                case "Smart Thermostat":
                    client.request(newDevice.getDeviceID(), Controller[2]);
                    stage.setScene(sceneList[2]);
                    break;
                case "Smart Coffee Machine":
                    client.request(newDevice.getDeviceID(), Controller[3]);
                    stage.setScene(sceneList[3]);
                    break;
                case "Smart Garage Door":
                    client.request(newDevice.getDeviceID(), Controller[4]);
                    stage.setScene(sceneList[4]);
                    break;
                case "Smart Smoke Detector":
                    client.request(newDevice.getDeviceID(), Controller[5]);
                    stage.setScene(sceneList[5]);
                    break;
                default:
                    System.out.println("Error: Device type not found");
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

    public void enableAdminControls(){
        settingsMenuButton.setVisible(true);
        settingsMenuButton.setDisable(false);

        welcomeUserLabel.setText("Welcome, Admin Y");

    }

    public void disableAdminControls(){
        settingsMenuButton.setVisible(false);
        settingsMenuButton.setDisable(true);

        welcomeUserLabel.setText("Welcome, X");
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
    public void update(AbstractDeviceMessage msg) {
        //dont worry about this, im doing bad programming practices, but it works
    }

    public void toggleUserListSelected(ActionEvent actionEvent) {
        userListActive = !userListActive;
        if(!userListActive){
                // changes the names of these labels to represent the new list
            settingsMenuButton.setText("Device Settings");
            elementListIndicatorLabel.setText("Connected Devices:");
                // changes the names of these menu items to represent their new functions
            toggleUserListMenuItem.setText("View User List");
            addNewElementMenuItem.setText("Add New Device");
            deleteElementMenuItem.setText("Delete Existing Device");
                // disables the now irrelevant menu items
            promoteUserMenuItem.setVisible(false);
            promoteUserMenuItem.setDisable(true);
            demoteUserMenuItem.setVisible(false);
            demoteUserMenuItem.setDisable(true);


        }
        else{
                // changes the names of these labels to represent the new list
            settingsMenuButton.setText("User Settings");
            elementListIndicatorLabel.setText("User Accounts:");
                // changes the names of these menu items to represent their new functions
            toggleUserListMenuItem.setText("View Device List");
            addNewElementMenuItem.setText("Add New User");
            deleteElementMenuItem.setText("Delete Existing User");
                // enables the now relevant menu items
            promoteUserMenuItem.setVisible(true);
            promoteUserMenuItem.setDisable(false);
            demoteUserMenuItem.setVisible(true);
            demoteUserMenuItem.setDisable(false);


        }
    }

    public void addNewElementSelected(ActionEvent actionEvent) {
    }

    public void deleteElementSelected(ActionEvent actionEvent) {
    }

    public void promoteUserSelected(ActionEvent actionEvent) {
    }

    public void demoteUserSelected(ActionEvent actionEvent) {
    }

    public void updateUserList(UserListMessage msg) {
        //recieves a single user from the server and adds it to the user list
        client.getUsers(this);

    }
}
