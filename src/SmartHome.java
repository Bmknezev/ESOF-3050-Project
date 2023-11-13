import GUI.Control.LoginMenuController;

import GUI.Control.DeviceSelectionMenuController;

import GUI.Control.SmartHomeClient;

import GUI.Control.SmartLightMenuController;
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

        // injecting device selection scene into the controller of the login scene as the next scene
        LoginMenuController loginPaneController = loginPaneLoader.getController();
        loginPaneController.setNextScene(deviceSelectionScene);

        // injecting the device selection scene into the controller of the light device scene as the previous scene
        SmartLightMenuController lightDevicePaneController = lightDevicePaneLoader.getController();
        lightDevicePaneController.setPreviousScene(deviceSelectionScene);

        // injecting the login scene into the controller of the device selection scene as the previous scene
        DeviceSelectionMenuController deviceSelectionPaneController = deviceSelectionPaneLoader.getController();
        deviceSelectionPaneController.setPreviousScene(loginScene);
        deviceSelectionPaneController.addNewDevice(lightDeviceScene, lightDevicePaneController.getSmartDevice());



        //injecting server connection into the controller of the second scene
        secondPaneController.addServer(s);

        //create new light object
        //SmartLight l = new SmartLight("light 1", true, -1,true,0x000000,100,false );
        if (!guiTest){

            //s.request(1, lightDevicePaneController);

            s.request(1, secondPaneController);
]
        }

        //secondPaneController.link(l);

        primaryStage.setTitle("Smart Home");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}
