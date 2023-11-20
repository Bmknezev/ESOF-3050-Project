package GUI.Control;

import GUI.Control.Abstract.AbstractDeviceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    @FXML
    private MenuButton settingsMenuButton;

    @FXML
    private MenuItem toggleUserListMenuItem;
    @FXML
    private MenuItem addNewDeviceMenuItem;
    @FXML
    private MenuItem deleteDeviceMenuItem;

    private Scene previous;
    private Scene[] sceneList = new Scene[5];
    private AbstractDeviceController[] Controller;

    private TextInputDialog manageUserMenu = new TextInputDialog("");

    private boolean userListActive = false;

    public void setPreviousScene(Scene previousScene) {
        previous = previousScene;
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
        //lists of all the device scenes and their controllers
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
                // changes the names of these to represent their new functions
            settingsMenuButton.setText("Device Settings");
            elementListIndicatorLabel.setText("Connected Devices:");
            toggleUserListMenuItem.setText("View User List");
                // enables the now relevant menu items
            addNewDeviceMenuItem.setVisible(true);
            addNewDeviceMenuItem.setDisable(false);
            deleteDeviceMenuItem.setVisible(true);
            deleteDeviceMenuItem.setDisable(false);

            deviceVBox.getChildren().clear();
            client.getDevices(this);
        }
        else{
                // changes the names of these to represent their new functions
            settingsMenuButton.setText("User Settings");
            elementListIndicatorLabel.setText("User Accounts:");
            toggleUserListMenuItem.setText("View Device List");
                // disables the now irrelevant menu items
            addNewDeviceMenuItem.setVisible(false);
            addNewDeviceMenuItem.setDisable(true);
            deleteDeviceMenuItem.setVisible(false);
            deleteDeviceMenuItem.setDisable(true);

            deviceVBox.getChildren().clear();
            client.getUsers(this);
        }
    }

    public void addNewDeviceSelected(ActionEvent actionEvent) {
    }

    public void deleteDeviceSelected(ActionEvent actionEvent) {
    }

    public void updateUserList(UserListMessage msg) {
            // this creates a new label for the users username
        Label usernameLabel = new Label();

            // these set the parameters of the label
        usernameLabel.setText(msg.getUsername());
        usernameLabel.setWrapText(true);
        usernameLabel.setFont(Font.font("System", FontWeight.BOLD, 15));

            // this creates a new label that shows the type of the new device
        Label userPermisionsLabel = new Label();
            // this sets the test of the label depending on the permissions of the user
        userPermisionsLabel.setText(msg.getAdmin() ? "Admin User" : "User");

            // this creates a new button that is linked to the new device
        Button manageUserButton = new Button("Manage User");
        // this sets the button to change the scene when pressed
        manageUserButton.setOnAction(event ->{
            manageUserMenu.setHeaderText(msg.getUsername() + "");
            DialogPane dp = manageUserMenu.getDialogPane();
            dp.setContent(new VBox(8,new TextField(), new TextField(), new Button()));
        });

        // this creates a new hbox to contain the new elements created
        HBox deviceControlHBox = new HBox();
        // these set the parameters of the hbox
        deviceControlHBox.setSpacing(15);
        deviceControlHBox.setAlignment(Pos.CENTER_RIGHT);
        // this adds the elements to the hbox
        deviceControlHBox.getChildren().addAll(userPermisionsLabel, manageUserButton);
        // this adds the hbox to the vbox

        StackPane deviceStackPane = new StackPane();
        deviceStackPane.setAlignment(Pos.CENTER_LEFT);

        deviceStackPane.getChildren().addAll(usernameLabel, deviceControlHBox);

        deviceVBox.getChildren().add(deviceStackPane);
    }
}
