import ClientServer.SmartHomeClient;
import GUI.Control.*;

import GUI.Control.Abstract.AbstractDeviceController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Platform.runLater;

public class SmartHome extends Application {
    static SmartHomeClient s = new SmartHomeClient("127.0.0.1", 19920);

    static boolean guiTest = false;

    public static void main(String[] args) {
        boolean connectionFailed = false;

        if (!guiTest){
            try {
                s.openConnection();
            } catch (IOException e){
                connectionFailed = true;
                //throw new RuntimeException(e);
                //create new stage and display error message
                runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Connection Error");
                    alert.setHeaderText("Connection to server failed.");
                    alert.setContentText("Please check your internet connection and ensure the server is online.");
                    alert.showAndWait();
                });
            }
        }
        if(!connectionFailed)
            launch(args);


    }
    @FXML
    public void stop(){
        System.out.println("Closing connection to server.");
        try {
            s.closeConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {


        // getting loader and a pane for the login scene
        FXMLLoader loginPaneLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/LoginMenu.fxml"));
        Parent loginPane = loginPaneLoader.load();
        Scene loginScene = new Scene(loginPane, 600, 575);

        // getting loader and a pane for the device selection scene
        FXMLLoader deviceSelectionPaneLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/DeviceSelectionMenu.fxml"));
        Parent deviceSelectionPane = deviceSelectionPaneLoader.load();
        Scene deviceSelectionScene = new Scene(deviceSelectionPane, 600, 575);

        // getting loader and a pane for the light device scene
        FXMLLoader lightDevicePaneLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/SmartLightMenu.fxml"));
        Parent lightDevicePane = lightDevicePaneLoader.load();
        Scene lightDeviceScene = new Scene(lightDevicePane, 600, 575);

        //getting loader and a pane for the lock device scene
        FXMLLoader lockDevicePaneLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/SmartLockMenu.fxml"));
        Parent lockDevicePane = lockDevicePaneLoader.load();
        Scene lockDeviceScene = new Scene(lockDevicePane, 600, 575);

        //getting loader and a pane for the thermostat device scene
        FXMLLoader thermostatDevicePaneLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/SmartThermostatMenu.fxml"));
        Parent thermostatDevicePane = thermostatDevicePaneLoader.load();
        Scene thermostatDeviceScene = new Scene(thermostatDevicePane, 600, 575);

        //getting loader and a pane for the coffee machine device scene
        FXMLLoader coffeeMakerDevicePaneLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/SmartCoffeeMakerMenu.fxml"));
        Parent coffeeMakerDevicePane = coffeeMakerDevicePaneLoader.load();
        Scene coffeeMakerDeviceScene = new Scene(coffeeMakerDevicePane, 600, 575);

        //getting loader and a pane for the garage door device scene
        FXMLLoader garageDoorOpenerDevicePaneLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/SmartGarageDoorOpenerMenu.fxml"));
        Parent garageDoorOpenerDevicePane = garageDoorOpenerDevicePaneLoader.load();
        Scene garageDoorOpenerDeviceScene = new Scene(garageDoorOpenerDevicePane, 600, 575);

        //getting loader and a pane for the smoke detector device scene
        FXMLLoader smokeDetectorDevicePaneLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/SmartSmokeDetectorMenu.fxml"));
        Parent smokeDetectorDevicePane = smokeDetectorDevicePaneLoader.load();
        Scene smokeDetectorDeviceScene = new Scene(smokeDetectorDevicePane, 600, 575);


        // injecting device selection scene into the controller of the login scene as the next scene
        LoginMenuController loginPaneController = loginPaneLoader.getController();
        loginPaneController.setNextScene(deviceSelectionScene, primaryStage);

        // injecting the device selection scene into the controller of the light device scene as the previous scene
        SmartLightMenuController lightDevicePaneController = lightDevicePaneLoader.getController();
        lightDevicePaneController.setPreviousScene(deviceSelectionScene);
        lightDeviceScene.setUserData(lightDevicePaneController);

        // injecting the login scene into the controller of the device selection scene as the previous scene
        DeviceSelectionMenuController deviceSelectionPaneController = deviceSelectionPaneLoader.getController();
        deviceSelectionPaneController.setPreviousScene(loginScene);
        //deviceSelectionPaneController.addNewDevice(lightDeviceScene, lightDevicePaneController.getSmartDevice());

        //injecting the device selection scene into the controller of the lock device scene as the previous scene
        SmartLockMenuController lockDevicePaneController = lockDevicePaneLoader.getController();
        lockDevicePaneController.setPreviousScene(deviceSelectionScene);
        lockDeviceScene.setUserData(lockDevicePaneController);

        //injecting the device selection scene into the controller of the thermostat device scene as the previous scene
        SmartThermostatMenuController thermostatDevicePaneController = thermostatDevicePaneLoader.getController();
        thermostatDevicePaneController.setPreviousScene(deviceSelectionScene);
        thermostatDeviceScene.setUserData(thermostatDevicePaneController);

        //injecting the device selection scene into the controller of the coffee machine device scene as the previous scene
        SmartCoffeeMakerMenuController coffeeMakerDevicePaneController = coffeeMakerDevicePaneLoader.getController();
        coffeeMakerDevicePaneController.setPreviousScene(deviceSelectionScene);
        coffeeMakerDeviceScene.setUserData(coffeeMakerDevicePaneController);

        //injecting the device selection scene into the controller of the garage door device scene as the previous scene
        SmartGarageDoorOpenerMenuController garageDoorOpenerDevicePaneController = garageDoorOpenerDevicePaneLoader.getController();
        garageDoorOpenerDevicePaneController.setPreviousScene(deviceSelectionScene);
        garageDoorOpenerDeviceScene.setUserData(garageDoorOpenerDevicePaneController);

        //injecting the device selection scene into the controller of the smoke detector device scene as the previous scene
        SmartSmokeDetectorMenuController smokeDetectorDevicePaneController = smokeDetectorDevicePaneLoader.getController();
        smokeDetectorDevicePaneController.setPreviousScene(deviceSelectionScene);
        smokeDetectorDeviceScene.setUserData(smokeDetectorDevicePaneController);







        //injecting server connection into the controller of the second scene
        lightDevicePaneController.addServer(s);
        lockDevicePaneController.addServer(s);
        thermostatDevicePaneController.addServer(s);
        coffeeMakerDevicePaneController.addServer(s);
        garageDoorOpenerDevicePaneController.addServer(s);
        smokeDetectorDevicePaneController.addServer(s);
        deviceSelectionPaneController.addServer(s);
        loginPaneController.addServer(s);





        //making list of device scenes to enable switching scenes
        Scene[] sceneList = new Scene[6];
        sceneList[0] = lightDeviceScene;
        sceneList[1] = lockDeviceScene;
        sceneList[2] = thermostatDeviceScene;
        sceneList[3] = coffeeMakerDeviceScene;
        sceneList[4] = garageDoorOpenerDeviceScene;
        sceneList[5] = smokeDetectorDeviceScene;


        //making list of device controllers to change values
        AbstractDeviceController[] Controller = new AbstractDeviceController[6];
        Controller[0] = lightDevicePaneController;
        Controller[1] = lockDevicePaneController;
        Controller[2] = thermostatDevicePaneController;
        Controller[3] = coffeeMakerDevicePaneController;
        Controller[4] = garageDoorOpenerDevicePaneController;
        Controller[5] = smokeDetectorDevicePaneController;

        //adding both lists to device selection menu controller
        deviceSelectionPaneController.addScene(sceneList, Controller);


        if (!guiTest){
            s.setLoginMenuController(loginPaneController);
            s.getDevices(deviceSelectionPaneController);
        }

        primaryStage.setTitle("Smart Home");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}
