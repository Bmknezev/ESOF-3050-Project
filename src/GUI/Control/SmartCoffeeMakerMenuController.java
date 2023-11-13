package GUI.Control;

import GUI.Control.Abstract.AbstractDeviceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class SmartCoffeeMakerMenuController extends AbstractDeviceController {

    @FXML
    private Button BrewCoffeeButton;

    @FXML
    private Button CreateAutomationButton;

    @FXML
    private Button EditAutomationsButton;

    @FXML
    private Button backButton;

    @FXML
    private ToggleGroup Size;

    @FXML
    private ImageView SmartDeviceImageView;

    @FXML
    private Label SmartDeviceNameLabel;

    @FXML
    private Label StatusIndicatorLabel;

    @FXML
    private ToggleGroup Strength;

    @FXML
    private ToggleGroup Temperature;

    private Scene first;
    public void setFirstScene(Scene firstScene) {
        first = firstScene;
    }

    @Override
    public void update(String[] s) {

    }

    @FXML
    void backButtonPressed(ActionEvent event) {

    }

}
