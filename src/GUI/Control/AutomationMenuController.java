package GUI.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class AutomationMenuController {
        // back button
    @FXML
    private Button backButton;
        // commit changes button
    @FXML
    private Button confirmAutomationButton;

    @FXML
    private ToggleGroup frequencySelection;

        // create buttons inside this vbox for the automatable actions
    @FXML
    private VBox smartDeviceActionsVBox;

    @FXML
    private TextField frequencyTextField;

    @FXML
    void backButtonPressed(ActionEvent event) {

    }

    @FXML
    void confirmAutomationButtonPressed(ActionEvent event) {

    }

}
