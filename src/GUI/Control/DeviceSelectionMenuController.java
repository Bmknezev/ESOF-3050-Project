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

import java.util.concurrent.atomic.AtomicInteger;

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
    private TextInputDialog newDeviceMenu = new TextInputDialog("");

    private boolean userListActive = false;

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
            dp.setContent(new VBox(8,new HBox(2, userLabel, userField), new HBox(2, passLabel, passField), adminCheckBox));

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

        deviceListMenuButton.setVisible(true);
        deviceListMenuButton.setDisable(false);
        toggleUserListButton.setVisible(true);
        toggleUserListButton.setDisable(false);
    }

    public void disableAdminControls(){
        //welcomeUserLabel.setText("Welcome, X");

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
        newDeviceMenu.setHeaderText("Select a device type");
        newDeviceMenu.setTitle("Add new device");
        DialogPane dp = newDeviceMenu.getDialogPane();
        RadioButton lightButton = new RadioButton("Smart Light");
        RadioButton lockButton = new RadioButton("Smart Lock");
        RadioButton thermostatButton = new RadioButton("Smart Thermostat");
        RadioButton coffeeMakerButton = new RadioButton("Smart Coffee Maker");
        RadioButton garageDoorButton = new RadioButton("Smart Garage Door");
        RadioButton smokeDetectorButton = new RadioButton("Smart Smoke Detector");
        ToggleGroup deviceTypeGroup = new ToggleGroup();
        lightButton.setToggleGroup(deviceTypeGroup);
        lockButton.setToggleGroup(deviceTypeGroup);
        thermostatButton.setToggleGroup(deviceTypeGroup);
        coffeeMakerButton.setToggleGroup(deviceTypeGroup);
        garageDoorButton.setToggleGroup(deviceTypeGroup);
        smokeDetectorButton.setToggleGroup(deviceTypeGroup);


        newDeviceMenu.setResultConverter((ButtonType button) -> {
            int deviceType ;
            if (button == ButtonType.OK) {
                if(lightButton.isSelected())
                    deviceType = 1;
                else if(lockButton.isSelected())
                    deviceType = 2;
                else if(thermostatButton.isSelected())
                    deviceType = 3;
                else if(coffeeMakerButton.isSelected())
                    deviceType = 4;
                else if(garageDoorButton.isSelected())
                    deviceType = 5;
                else if(smokeDetectorButton.isSelected())
                    deviceType = 6;
                else
                    deviceType = 0;
                nextMenu(deviceType);
            }

            return null;
        });

        dp.setContent(new VBox(8, lightButton, lockButton, thermostatButton, coffeeMakerButton, garageDoorButton, smokeDetectorButton));
        newDeviceMenu.show();

    }

    private void nextMenu(int deviceType){
        Platform.runLater(() -> {
        switch (deviceType){
            case 1:
                //light
                newDeviceMenu.setHeaderText("Add new light");
                newDeviceMenu.setTitle("Add new light");
                DialogPane dp = newDeviceMenu.getDialogPane();
                Label nameLabel = new Label("Name: ");
                TextField nameField = new TextField();
                newDeviceMenu.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.OK) {
                        deviceVBox.getChildren().clear();
                        client.UpdateServer(new NewDeviceMessage(-1, nameField.getText(), "Smart Light"));
                    }
                    return null;
                });
                dp.setContent(new VBox(8,new HBox(2, nameLabel, nameField)));
                newDeviceMenu.show();
                break;
            case 2:
                //lock
                newDeviceMenu.setHeaderText("Add new lock");
                newDeviceMenu.setTitle("Add new lock");
                DialogPane dp2 = newDeviceMenu.getDialogPane();
                Label nameLabel2 = new Label("Name: ");
                TextField nameField2 = new TextField();
                newDeviceMenu.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.OK) {
                        deviceVBox.getChildren().clear();
                        client.UpdateServer(new NewDeviceMessage(-1, nameField2.getText(), "Smart Lock"));
                    }
                    return null;
                });
                dp2.setContent(new VBox(8,new HBox(2, nameLabel2, nameField2)));
                newDeviceMenu.show();
                break;
            case 3:
                //thermostat
                newDeviceMenu.setHeaderText("Add new thermostat");
                newDeviceMenu.setTitle("Add new thermostat");
                DialogPane dp3 = newDeviceMenu.getDialogPane();
                Label nameLabel3 = new Label("Name: ");
                TextField nameField3 = new TextField();
                newDeviceMenu.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.OK) {
                        deviceVBox.getChildren().clear();
                        client.UpdateServer(new NewDeviceMessage(-1, nameField3.getText(), "Smart Thermostat"));
                    }
                    return null;
                });
                dp3.setContent(new VBox(8,new HBox(2, nameLabel3, nameField3)));
                newDeviceMenu.show();
                break;
            case 4:
                //coffee maker
                newDeviceMenu.setHeaderText("Add new coffee maker");
                newDeviceMenu.setTitle("Add new coffee maker");
                DialogPane dp4 = newDeviceMenu.getDialogPane();
                Label nameLabel4 = new Label("Name: ");
                TextField nameField4 = new TextField();
                newDeviceMenu.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.OK) {
                        deviceVBox.getChildren().clear();
                        client.UpdateServer(new NewDeviceMessage(-1, nameField4.getText(), "Smart Coffee Maker"));
                    }
                    return null;
                });
                dp4.setContent(new VBox(8,new HBox(2, nameLabel4, nameField4)));
                newDeviceMenu.show();
                break;
            case 5:
                //garage door
                newDeviceMenu.setHeaderText("Add new garage door");
                newDeviceMenu.setTitle("Add new garage door");
                DialogPane dp5 = newDeviceMenu.getDialogPane();
                Label nameLabel5 = new Label("Name: ");
                TextField nameField5 = new TextField();
                newDeviceMenu.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.OK) {
                        deviceVBox.getChildren().clear();
                        client.UpdateServer(new NewDeviceMessage(-1, nameField5.getText(), "Smart Garage Door"));
                    }
                    return null;
                });
                dp5.setContent(new VBox(8,new HBox(2, nameLabel5, nameField5)));
                newDeviceMenu.show();
                break;
            case 6:
                //smoke detector
                newDeviceMenu.setHeaderText("Add new smoke detector");
                newDeviceMenu.setTitle("Add new smoke detector");
                DialogPane dp6 = newDeviceMenu.getDialogPane();
                Label nameLabel6 = new Label("Name: ");
                TextField nameField6 = new TextField();
                newDeviceMenu.setResultConverter((ButtonType button) -> {
                    if (button == ButtonType.OK) {
                        deviceVBox.getChildren().clear();
                        client.UpdateServer(new NewDeviceMessage(-1, nameField6.getText(), "Smart Smoke Detector"));
                    }
                    return null;
                });
                dp6.setContent(new VBox(8,new HBox(2, nameLabel6, nameField6)));
                newDeviceMenu.show();
                break;
            default:
                System.out.println("Error: Device type not found");
                break;
        }
        });
    }

    public void deleteDeviceSelected(ActionEvent actionEvent) {
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
