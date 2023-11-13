import ClientServer.SmartHomeClient;
import GUI.Control.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SmartHome extends Application {
    static SmartHomeClient s = new SmartHomeClient("127.0.0.1", 19920);

    static boolean guiTest = false;
    public static void main(String[] args) {

        if (!guiTest){
            try {
                s.openConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        launch(args);
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

        FXMLLoader lockDevicePaneLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/SmartLockMenu.fxml"));
        Parent lockDevicePane = lockDevicePaneLoader.load();
        Scene lockDeviceScene = new Scene(lockDevicePane, 600, 575);

        FXMLLoader thermostatDevicePaneLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/SmartThermostatMenu.fxml"));
        Parent thermostatDevicePane = thermostatDevicePaneLoader.load();
        Scene thermostatDeviceScene = new Scene(thermostatDevicePane, 600, 575);

        // injecting device selection scene into the controller of the login scene as the next scene
        LoginMenuController loginPaneController = loginPaneLoader.getController();
        loginPaneController.setNextScene(deviceSelectionScene);

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
        //SmartThermostatMenuController thermostatDevicePaneController = thermostatDevicePaneLoader.getController();
        //thermostatDevicePaneController.setPreviousScene(deviceSelectionScene);
        //thermostatDeviceScene.setUserData(thermostatDevicePaneController);


        //injecting server connection into the controller of the second scene
        lightDevicePaneController.addServer(s);
        lockDevicePaneController.addServer(s);
        deviceSelectionPaneController.addServer(s);

        Scene[] sceneList = new Scene[5];
        sceneList[0] = lightDeviceScene;
        sceneList[1] = lockDeviceScene;

        AbstractController[] Controller = new AbstractController[5];
        Controller[0] = lightDevicePaneController;
        Controller[1] = lockDevicePaneController;

        deviceSelectionPaneController.addScene(sceneList, Controller);


        if (!guiTest){
            s.getDevices(deviceSelectionPaneController);
        }

        primaryStage.setTitle("Smart Home");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}
