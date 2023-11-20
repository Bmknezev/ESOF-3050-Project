package GUI.Control;

import GUI.Control.Abstract.AbstractDeviceController;
import javafx.application.Platform;
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
import messages.UserListMessage;

public class DeviceSelectionMenuController extends AbstractDeviceController {


    public Button addUserButton;
    @FXML
    private Button backButton;

    @FXML
    private Button toggleUserListButton;

    @FXML
    private Label welcomeUserLabel;

    @FXML
    private Label elementListIndicatorLabel;

    @FXML
    private VBox deviceVBox;

    @FXML
    private MenuButton deviceListMenuButton;

    @FXML
    private MenuItem addNewDeviceMenuItem;
    @FXML
    private MenuItem deleteDeviceMenuItem;

    private Scene previous;
    private Scene[] sceneList = new Scene[5];
    private AbstractDeviceController[] Controller;

    private TextInputDialog manageUserMenu = new TextInputDialog("");
    private TextInputDialog addUserMenu = new TextInputDialog("");

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

        Platform.runLater(() -> deviceVBox.getChildren().add(deviceStackPane));

    }

    public void enableAdminControls(){
        welcomeUserLabel.setText("Welcome, Admin Y");

        if (userListActive){
            toggleUserList();
        }

        deviceListMenuButton.setVisible(true);
        deviceListMenuButton.setDisable(false);
        toggleUserListButton.setVisible(true);
        toggleUserListButton.setDisable(false);
    }

    public void disableAdminControls(){
        welcomeUserLabel.setText("Welcome, X");

        if (userListActive){
            toggleUserList();
        }

        deviceListMenuButton.setVisible(false);
        deviceListMenuButton.setDisable(true);
        toggleUserListButton.setVisible(false);
        toggleUserListButton.setDisable(true);
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

    public void toggleUserListButtonPressed(ActionEvent actionEvent) {
        toggleUserList();
    }

    private void toggleUserList(){
        userListActive = !userListActive;
        if(!userListActive){
            System.out.println("Device List Active");
            // changes the names of these to represent their new functions
            elementListIndicatorLabel.setText("Connected Devices:");
            toggleUserListButton.setText("View User List");

            deviceListMenuButton.setVisible(true);
            deviceListMenuButton.setDisable(false);
            addUserButton.setVisible(false);
            addUserButton.setDisable(true);

            deviceVBox.getChildren().clear();
            client.getDevices(this);
        }
        else{
            System.out.println("User list selected");
            // changes the names of these to represent their new functions
            elementListIndicatorLabel.setText("User Accounts:");
            toggleUserListButton.setText("View Device List");

            deviceListMenuButton.setVisible(false);
            deviceListMenuButton.setDisable(true);
            addUserButton.setVisible(true);
            addUserButton.setDisable(false);

            deviceVBox.getChildren().clear();
            client.getUsers(this);
        }
    }

    public void addNewDeviceSelected(ActionEvent actionEvent) {
    }

    public void deleteDeviceSelected(ActionEvent actionEvent) {
    }

    public void updateUserList(UserListMessage msg) {
        System.out.println("User added.");
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
            // this sets the button to open a new window when pressed
        manageUserButton.setOnAction(event ->{
            DialogPane dp = manageUserMenu.getDialogPane();
            Label userLabel = new Label("Username: ");
            Label passLabel = new Label("Password: ");
            TextField userField = new TextField();
            TextField passField = new TextField();
            CheckBox adminCheckBox = new CheckBox("Admin");

            userField.setText(msg.getUsername());
            passField.setText(msg.getPassword());
            adminCheckBox.setSelected(msg.getAdmin());
            manageUserMenu.setResultConverter((ButtonType button) -> {
                if (button == ButtonType.OK) {
                    deviceVBox.getChildren().clear();
                    modifyUser(msg.getUserID(), userField.getText(), passField.getText(), adminCheckBox.isSelected());
                }
                return null;
            });
            dp.setContent(new VBox(8,new HBox(2, userLabel, userField), new HBox(2, passLabel, passField), adminCheckBox));

            manageUserMenu.show();
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

        Platform.runLater(() -> deviceVBox.getChildren().add(deviceStackPane));

    }

    private void modifyUser(int id, String text, String text1, boolean selected) {
        UserListMessage msg = new UserListMessage(id,text, text1, selected, false);
        client.UpdateServer(msg);
    }

    public void addUserButtonPressed(ActionEvent actionEvent) {
        addUserMenu.setHeaderText("Add new user");
        DialogPane dp = addUserMenu.getDialogPane();
        Label usernameLabel = new Label("Username: ");
        Label passwordLabel = new Label("Password: ");
        TextField usernameField = new TextField();
        TextField passwordField = new TextField();
        CheckBox adminCheckBox = new CheckBox("Admin");
        addUserMenu.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                deviceVBox.getChildren().clear();
                addUser(usernameField.getText(), passwordField.getText(), adminCheckBox.isSelected());
            }
            return null;
        });
        dp.setContent(new VBox(8,new HBox(2, usernameLabel, usernameField), new HBox(2, passwordLabel, passwordField), adminCheckBox));

        addUserMenu.show();
    }

    private void addUser(String text, String text1, boolean selected) {
        UserListMessage msg = new UserListMessage(-2,text, text1, selected, true);
        client.UpdateServer(msg);
    }

    public void error(UserListMessage msg) {
        System.out.println("User already exists.");
        Platform.runLater(() ->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User already exists");
            alert.setContentText("The user " + msg.getUsername() + " already exists.");
            alert.showAndWait();
        });
    }
}
