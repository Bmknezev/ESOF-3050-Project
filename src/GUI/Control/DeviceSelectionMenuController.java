//-----------------------------------------------------------------
// DeviceSelectionMenuController.java
// Group 2
// Description: This class manages the device and user selection menu in the Smart Home system. It handles
//              user interactions related to adding new devices, managing existing devices, managing user accounts,
//              and navigating back to the previous menu.
// Created By: Francisco
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - backButton: Button - Button for navigating back
//   - toggleUserListButton: Button - Button for toggling user list display
//   - welcomeUserLabel: Label - Label displaying the welcome message for users
//   - elementListIndicatorLabel: Label - Label indicating the displayed list of elements (devices/users)
//   - listVBox: VBox - VBox containing the list of devices/users
//   - manageItemListMenuButton: MenuButton - Button for managing the device/user list
//   - addItemOption: MenuItem - Menu option for adding a new device/user
//   - deleteItemOption: MenuItem - Menu option for deleting an existing device/user
//   - previous: Scene - Reference to the previous scene
//   - sceneList: Scene[] - Array of scenes related to device operations
//   - Controller: AbstractDeviceController[] - Array of device controllers
//   - manageUserMenu: TextInputDialog - Dialog for managing user accounts
//   - addUserMenu: TextInputDialog - Dialog for adding a new user
//   - newDeviceMenu: TextInputDialog - Dialog for adding a new device
//   - deleteItemMenu: TextInputDialog - Dialog for deleting an item (device/user)
//   - userListActive: boolean - Flag indicating whether the user list is active or not
//-----------------------------------------------------------------


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
import messages.NewDeviceMessage;
import messages.UserListMessage;
import messages.client.Listable;

    public class DeviceSelectionMenuController extends AbstractDeviceController {

    @FXML
    private Button backButton;

    @FXML
    private Button toggleUserListButton;

    @FXML
    private Label welcomeUserLabel;

    @FXML
    private Label elementListIndicatorLabel;

    @FXML
    private VBox listVBox;

    @FXML
    private MenuButton manageItemListMenuButton;

    @FXML
    private MenuItem addItemOption;
    @FXML
    private MenuItem deleteItemOption;

    private Scene previous;
    private Scene[] sceneList = new Scene[5];
    private AbstractDeviceController[] Controller;

    private TextInputDialog manageUserMenu = new TextInputDialog("");
    private TextInputDialog addUserMenu = new TextInputDialog("");
    private TextInputDialog newDeviceMenu = new TextInputDialog("");
    private TextInputDialog deleteItemMenu = new TextInputDialog("");

    private boolean userListActive = false, delete = false;

    public void setPreviousScene(Scene previousScene) {
        previous = previousScene;
    }

    public Button createNewListItem(Listable listableItem){

        Label nameLabel = new Label();

        nameLabel.setText(listableItem.getNameListable());
        nameLabel.setWrapText(true);
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 15));

        Label categoryLabel = new Label();

        categoryLabel.setText(listableItem.getCategoryListable());

        Button managementButton = new Button();

        // this creates a new hbox to contain the new elements created
        HBox listableItemControlHBox = new HBox();
        // these set the parameters of the hbox
        listableItemControlHBox.setSpacing(15);
        listableItemControlHBox.setAlignment(Pos.CENTER_RIGHT);
        // this adds the elements to the hbox
        listableItemControlHBox.getChildren().addAll(categoryLabel, managementButton);
        // this adds the hbox to the vbox

        StackPane listableItemStackPane = new StackPane();
        listableItemStackPane.setAlignment(Pos.CENTER_LEFT);

        listableItemStackPane.getChildren().addAll(nameLabel, listableItemControlHBox);

        Label idLabel = new Label();
        idLabel.setText(Integer.toString(listableItem.getIDListable()));
        idLabel.setVisible(false);
        listableItemStackPane.getChildren().add(idLabel);

        Platform.runLater(() -> listVBox.getChildren().add(listableItemStackPane));


        return managementButton;
    }

    public void addNewDevice(NewDeviceMessage newDevice) {
        System.out.println("Adding new device" + newDevice.getDeviceName());
            Button deviceManagementButton = createNewListItem(newDevice);
            deviceManagementButton.setText("Manage Device");

            deviceManagementButton.setOnAction(event -> {
                Stage stage = (Stage) deviceManagementButton.getScene().getWindow();

                client.request(newDevice.getDeviceID(), Controller[newDevice.getDeviceTypeNumber()]);
                stage.setScene(sceneList[newDevice.getDeviceTypeNumber()]);
            });

    }

    public void updateUserList(UserListMessage newUser) {
        Button userManagementButton = createNewListItem(newUser);
        userManagementButton.setText("Manage User");

        userManagementButton.setOnAction(event ->{
            manageUserMenu.setHeaderText(newUser.getUsername() + " settings");
            DialogPane dp = manageUserMenu.getDialogPane();
            Label userLabel = new Label("Username: ");
            Label passLabel = new Label("Password: ");
            TextField userField = new TextField();
            TextField passField = new TextField();
            CheckBox adminCheckBox = new CheckBox("Admin");

            userField.setText(newUser.getUsername());
            passField.setText(newUser.getPassword());
            adminCheckBox.setSelected(newUser.getAdmin());
            manageUserMenu.setResultConverter((ButtonType button) -> {
                if (button == ButtonType.OK) {
                    listVBox.getChildren().clear();
                    System.out.println("Modifying user " + newUser.getUsername() + " " + newUser.getUserID());
                    modifyUser(newUser.getUserID(), userField.getText(), passField.getText(), adminCheckBox.isSelected());
                    System.out.println("User modified");
                }
                return null;
            });
            dp.setContent(new VBox(8,new HBox(2, userLabel, userField), new HBox(4, passLabel, passField), adminCheckBox));

            manageUserMenu.show();
        });
    }

    public void setWelcomeLabel(boolean admin, String username) {
        Platform.runLater(() -> welcomeUserLabel.setText("Welcome, " + (admin ? "Admin " + username : username)));
    }

    public void enableAdminControls(){
        //welcomeUserLabel.setText("Welcome, Admin Y");

        if (userListActive){
            toggleUserList();
        }

        manageItemListMenuButton.setVisible(true);
        manageItemListMenuButton.setDisable(false);
        toggleUserListButton.setVisible(true);
        toggleUserListButton.setDisable(false);
    }

    public void disableAdminControls(){
        //welcomeUserLabel.setText("Welcome, X");

        if (userListActive){
            toggleUserList();
        }

        manageItemListMenuButton.setVisible(false);
        manageItemListMenuButton.setDisable(true);
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
            manageItemListMenuButton.setText("Manage Device List");
            addItemOption.setText("Add New Device");
            deleteItemOption.setText("Delete Existing Device");

            listVBox.getChildren().clear();
            client.getDevices(this);
        }
        else{
            System.out.println("User list selected");
            // changes the names of these to represent their new functions
            elementListIndicatorLabel.setText("User Accounts:");
            toggleUserListButton.setText("View Device List");
            manageItemListMenuButton.setText("Manage User List");
            addItemOption.setText("Add New User");
            deleteItemOption.setText("Delete Existing User");

            listVBox.getChildren().clear();
            client.getUsers(this);
        }
    }

    public void addItemSelected(ActionEvent actionEvent) {
        if (!userListActive) {
            createNewDeviceFirstMenu();
        }
        else{
            createNewUser();
        }
    }

    private void createNewDeviceFirstMenu(){
        // these give the user instructions
        newDeviceMenu.setHeaderText("Select a device type");
        newDeviceMenu.setTitle("Add new device");

        DialogPane dp = newDeviceMenu.getDialogPane();

        VBox dpContent = new VBox(8);

        ToggleGroup deviceTypeGroup = new ToggleGroup();
        RadioButton[] deviceTypeSelectionButtons = new RadioButton[6];

        // these create all the radio buttons and add them to the toggle group
        for (int i = 0; i < deviceTypeSelectionButtons.length; i++) {
            deviceTypeSelectionButtons[i] = new RadioButton();
            deviceTypeSelectionButtons[i].setToggleGroup(deviceTypeGroup);
            dpContent.getChildren().add(deviceTypeSelectionButtons[i]);
        }
        // these add the labels to all the radio buttons
        deviceTypeSelectionButtons[0].setText("Smart Light");
        deviceTypeSelectionButtons[1].setText("Smart Lock");
        deviceTypeSelectionButtons[2].setText("Smart Thermostat");
        deviceTypeSelectionButtons[3].setText("Smart Coffee Machine");
        deviceTypeSelectionButtons[4].setText("Smart Garage Door");
        deviceTypeSelectionButtons[5].setText("Smart Smoke Detector");

        newDeviceMenu.setResultConverter((ButtonType button) -> {
            int deviceType = 0;
            String deviceTypeString = "";
            if (button == ButtonType.OK) {
                for (int i = 0; i < deviceTypeSelectionButtons.length; i++) {
                    if (deviceTypeSelectionButtons[i].isSelected()) {
                        deviceType = i;
                        deviceTypeString = deviceTypeSelectionButtons[i].getText();
                        break;
                    }
                }
                createNewDeviceSecondMenu(deviceType, deviceTypeString);
            }

            return null;
        });

        dp.setContent(dpContent);
        newDeviceMenu.show();
    }

    private void createNewDeviceSecondMenu(int deviceType, String deviceTypeString){
            Platform.runLater(() -> {
                // this checks if there was an error in the previous menu
                if (deviceType < 0 || deviceType > 5){
                    System.out.println("Error: Device type not found");
                    return;
                }
                // these provide instruction to the user
                newDeviceMenu.setHeaderText("Add new " + deviceTypeString);
                newDeviceMenu.setTitle("Add new " + deviceTypeString);

                DialogPane dp = newDeviceMenu.getDialogPane();
                // this creates a section for the user to enter their name
                Label nameLabel = new Label("Name: ");
                TextField nameField = new TextField();
                // this creates a section for the user to enther their pin
                Label pinLabel = new Label("PIN: ");
                TextField pinField = new TextField();

                VBox labelVBox = new VBox(8, nameLabel),
                        textFieldVBox = new VBox(8, nameField);
                // this combines the label and textfield vboxes into one hbox
                labelVBox.setAlignment(Pos.CENTER);
                textFieldVBox.setAlignment(Pos.CENTER);

                HBox inputHBox = new HBox(2, labelVBox, textFieldVBox);
                inputHBox.setAlignment(Pos.CENTER);
                // this creates the vbox that will hold all the elements in the display box
                VBox dpContent = new VBox(8, inputHBox);
                dpContent.setAlignment(Pos.CENTER);

                // this adds the pin section to the display box only if necessary
                if (deviceType == 1 || deviceType == 4){
                    labelVBox.getChildren().add(pinLabel);
                    textFieldVBox.getChildren().add(pinField);
                }

                newDeviceMenu.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.OK) {
                        if (nameField.getText().isEmpty()){
                            nameField.setText("Untitled " + deviceTypeString);
                        }
                        listVBox.getChildren().clear();
                        System.out.println("Adding new " + deviceTypeString);
                        if(deviceType == 1 || deviceType == 4){
                            NewDeviceMessage msg = new NewDeviceMessage(-1, nameField.getText(), deviceTypeString);
                            msg.setPIN(Integer.parseInt(pinField.getText()));
                            client.UpdateServer(msg);
                        }else
                            client.UpdateServer(new NewDeviceMessage(-1, nameField.getText(), deviceTypeString));
                    }
                    return null;
                });

                dp.setContent(dpContent);
                newDeviceMenu.show();
            });
        }

    private void createNewUser(){
        addUserMenu.setHeaderText("Add new user");
        DialogPane dp = addUserMenu.getDialogPane();
        Label usernameLabel = new Label("Username: ");
        Label passwordLabel = new Label("Password: ");
        TextField usernameField = new TextField();
        TextField passwordField = new TextField();
        CheckBox adminCheckBox = new CheckBox("Admin");
        addUserMenu.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                listVBox.getChildren().clear();
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


    public void deleteItemSelected(ActionEvent actionEvent) {
        if (!userListActive) {
            deleteDevice();
        }
        else{
            deleteUser();
        }
    }

    private void deleteDevice(){
        //set up dialog box
        boolean empty = true;
        VBox dpContent = new VBox(8);
        deleteItemMenu.setHeaderText("Select a device to delete");
        deleteItemMenu.setTitle("Delete device");
        //create radio buttons
        RadioButton[] deviceSelectionButton = new RadioButton[listVBox.getChildren().size()];
        //create toggle group
        ToggleGroup deviceTypeGroup = new ToggleGroup();
        //get dialog pane
        DialogPane dp = deleteItemMenu.getDialogPane();

        for(int i = 0; i < deviceSelectionButton.length; i++) {
            //create new radio buttons for each device that is currently added to the system, and add them to the toggle group
            deviceSelectionButton[i] = new RadioButton(((Label)((StackPane)listVBox.getChildren().get(i)).getChildren().get(0)).getText());
            deviceSelectionButton[i].setToggleGroup(deviceTypeGroup);
            dpContent.getChildren().add(deviceSelectionButton[i]);
            empty = false;
        }

        //set up check for when the user presses ok
        deleteItemMenu.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                for(int i = 0; i < deviceSelectionButton.length; i++) {
                    if(deviceSelectionButton[i].isSelected()){
                        //get device id from the label of the stack pane
                        int id = Integer.parseInt(((Label)((StackPane)listVBox.getChildren().get(i)).getChildren().get(2)).getText());
                        //send the delete message to the server
                        client.UpdateServer(new NewDeviceMessage(id, deviceSelectionButton[i].getText(), "delete"));
                    }
                }
                //clear the device list
                listVBox.getChildren().clear();
            }

            return null;
        });

        //set the dialog pane content to the vbox containing the radio buttons
        if(empty) {
            dp.setContent(new Label("No devices to delete"));
            deleteItemMenu.setHeaderText("No devices to delete");
        }
        else
            dp.setContent(dpContent);
        //show the dialog box
        deleteItemMenu.show();
    }

    private void deleteUser(){
        //set up dialog box
        boolean empty = true;
        VBox dpContent = new VBox(8);
        deleteItemMenu.setHeaderText("Select a user to delete");
        deleteItemMenu.setTitle("Delete user");
        //create radio buttons
        RadioButton[] userSelectionButton = new RadioButton[listVBox.getChildren().size()];
        //create toggle group
        ToggleGroup userTypeGroup = new ToggleGroup();
        //get dialog pane
        DialogPane dp = deleteItemMenu.getDialogPane();

        for(int i = 0; i < userSelectionButton.length; i++) {
            //create new radio buttons for each device that is currently added to the system, and add them to the toggle group
            userSelectionButton[i] = new RadioButton(((Label)((StackPane)listVBox.getChildren().get(i)).getChildren().get(0)).getText());
            userSelectionButton[i].setToggleGroup(userTypeGroup);
            dpContent.getChildren().add(userSelectionButton[i]);
            empty = false;
        }

        //set up check for when the user presses ok
        deleteItemMenu.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                for(int i = 0; i < userSelectionButton.length; i++) {
                    if(userSelectionButton[i].isSelected()){
                        //get device id from the label of the stack pane
                        int id = Integer.parseInt(((Label)((StackPane)listVBox.getChildren().get(i)).getChildren().get(2)).getText());
                        String username = welcomeUserLabel.getText().substring(15);
                        //send the delete message to the server
                        client.UpdateServer(new UserListMessage(id, username, "delete", false, false));
                    }
                }
                //clear the device list
                listVBox.getChildren().clear();
            }

            return null;
        });

        //set the dialog pane content to the vbox containing the radio buttons
        if(empty) {
            dp.setContent(new Label("No users to delete"));
            deleteItemMenu.setHeaderText("No users to delete");
        }
        else
            dp.setContent(dpContent);
        //show the dialog box
        deleteItemMenu.show();
    }

    private void modifyUser(int id, String text, String text1, boolean selected) {
        UserListMessage msg = new UserListMessage(id,text, text1, selected, false);
        client.UpdateServer(msg);
    }
    public void error(UserListMessage msg) {
        Platform.runLater(() ->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            if(msg.getUserID() == -1)
                alert.setHeaderText("Username already taken");
            else if(msg.getUserID() == -2)
                alert.setHeaderText("Cannot delete current user");
            else if(msg.getUserID() == -3){
                alert.setHeaderText("Cannot remove last admin");
                alert.setContentText("The user " + msg.getUsername() + " is the only admin on the system.");
            }else if(msg.getUserID() == -4)
                alert.setHeaderText("Username and password cannot be empty");
            alert.showAndWait();
        });
    }

    public void clearList(){
        Platform.runLater(() -> listVBox.getChildren().clear());
    }
}