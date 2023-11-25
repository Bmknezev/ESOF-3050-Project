    // this is for testing
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
    private VBox deviceVBox;

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
    private TextInputDialog deleteDeviceMenu = new TextInputDialog("");

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

        Platform.runLater(() -> deviceVBox.getChildren().add(listableItemStackPane));


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
                    deviceVBox.getChildren().clear();
                    modifyUser(newUser.getUserID(), userField.getText(), passField.getText(), adminCheckBox.isSelected());
                }
                return null;
            });
            dp.setContent(new VBox(8,new HBox(2, userLabel, userField), new HBox(4, passLabel, passField), adminCheckBox));

            manageUserMenu.show();
        });
    }

    public void setWelcomeLabel(boolean admin, String username) {
        Platform.runLater(() ->{

            welcomeUserLabel.setText("Welcome, " + (admin ? "Admin " + username : username));
        });
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

            deviceVBox.getChildren().clear();
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

            deviceVBox.getChildren().clear();
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
        RadioButton deviceTypeSelectionButtons[] = new RadioButton[6];

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
                        deviceVBox.getChildren().clear();
                        System.out.println("Adding new " + deviceTypeString);
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


    public void deleteItemSelected(ActionEvent actionEvent) {
        if (!userListActive) {
            deleteDeviceMenu.setHeaderText("Select devices to delete");
            deleteDeviceMenu.setTitle("Delete device");
            DialogPane dp = deleteDeviceMenu.getDialogPane();

            VBox dpContent = new VBox(8);
            CheckBox[] deviceSelectionCheckBoxes = new CheckBox[deviceVBox.getChildren().size()];
            for (int i = 0; i < deviceSelectionCheckBoxes.length; i++) {
                deviceSelectionCheckBoxes[i] = new CheckBox(((Label) ((StackPane) deviceVBox.getChildren().get(i)).getChildren().get(0)).getText());
                dpContent.getChildren().add(deviceSelectionCheckBoxes[i]);
            }
            deleteDeviceMenu.setResultConverter((ButtonType button) -> {
                if (button == ButtonType.OK) {
                    for (int i = 0; i < deviceSelectionCheckBoxes.length; i++) {
                        if (deviceSelectionCheckBoxes[i].isSelected()) {
                            client.UpdateServer(new NewDeviceMessage(i, deviceSelectionCheckBoxes[i].getText(), "delete"));
                            deviceVBox.getChildren().clear();
                        }
                    }
                }
                return null;
            });
            dp.setContent(dpContent);
            deleteDeviceMenu.show();
        }
        else {

        }
        VBox dpContent = new VBox(8);
        RadioButton[] deviceSelectionButton = new RadioButton[deviceVBox.getChildren().size()];
        ToggleGroup deviceTypeGroup = new ToggleGroup();
        for(int i = 0; i < deviceSelectionButton.length; i++) {
            deviceSelectionButton[i] = new RadioButton(((Label)((StackPane)deviceVBox.getChildren().get(i)).getChildren().get(0)).getText());
            deviceSelectionButton[i].setToggleGroup(deviceTypeGroup);
            dpContent.getChildren().add(deviceSelectionButton[i]);
        }
        deleteDeviceMenu.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                for(int i = 0; i < deviceSelectionButton.length; i++) {
                    if(deviceSelectionButton[i].isSelected()){
                        int id = Integer.parseInt(((Label)((StackPane)deviceVBox.getChildren().get(i)).getChildren().get(2)).getText());
                        client.UpdateServer(new NewDeviceMessage(id, deviceSelectionButton[i].getText(), "delete"));
                    }
                }
                deviceVBox.getChildren().clear();
            }

            return null;
        });
        //dp.setContent(dpContent);
        deleteDeviceMenu.show();
    }

    private void deleteDevice(){

    }

    private void deleteUser(){

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
                alert.setHeaderText("User does not exist");
            else if(msg.getUserID() == -3){
                alert.setHeaderText("Cannot remove last admin");
                alert.setContentText("The user " + msg.getUsername() + " is the only admin on the system.");
            }
            alert.showAndWait();
        });
    }
}
