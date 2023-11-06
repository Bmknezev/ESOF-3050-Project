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
        Parent root = FXMLLoader.load(getClass().getResource("GUI/FXML/LoginMenu.fxml"));
        primaryStage.setTitle("Smart Home");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
