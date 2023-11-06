import GUI.Control.LoginMenuController;
import GUI.Control.SmartCoffeeMakerMenuController;
import GUI.Control.SmartLightMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SmartHome extends Application {

    public static void main(String[] args) {
        /*
        SmartHomeClient s = new SmartHomeClient("127.0.0.1", 19920);
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        //create new light object
        light l = new light(100, 0.5f);

        try {
            s.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/LoginMenu.fxml"));
        Parent firstPane = firstPaneLoader.load();
        Scene firstScene = new Scene(firstPane, 600, 575);

        // getting loader and a pane for the second scene
        FXMLLoader secondPaneLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/SmartLightMenu.fxml"));
        Parent secondPane = secondPaneLoader.load();
        Scene secondScene = new Scene(secondPane, 600, 575);

        // injecting second scene into the controller of the first scene
        LoginMenuController firstPaneController = firstPaneLoader.getController();
        firstPaneController.setSecondScene(secondScene);

        // injecting first scene into the controller of the second scene
        SmartLightMenuController secondPaneController = secondPaneLoader.getController();
        secondPaneController.setFirstScene(firstScene);

        primaryStage.setTitle("Smart Home");
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }
}
