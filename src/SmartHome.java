import GUI.Control.LoginMenuController;
import GUI.Control.SmartLightMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

    // aim for router 10.100.64.1

public class SmartHome extends Application {
    static SmartHomeClient s = new SmartHomeClient("10.100.65.45", 19920);
    public static void main(String[] args) {

        try {
            s.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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

        //create new light object
        //SmartLight l = new SmartLight("light 1", true, -1,true,0x000000,100,false );
        s.request(1, secondPaneController);

        //secondPaneController.link(l);


        primaryStage.setTitle("Smart Home");
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }
}
