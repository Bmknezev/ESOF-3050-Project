package GUI.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class AutomationMenuController {

    @FXML
    private Button backButton;

    @FXML
    private Button confirmAutomationButton;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ToggleGroup endDateSelection;

    @FXML
    private ToggleGroup repeatFrequencySelection;

    @FXML
    private TextField repeatFrequencyTextField;

    @FXML
    private ToggleGroup repeatSelection;

    @FXML
    private VBox smartDeviceActionsVBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    void backButtonPressed(ActionEvent event) {

    }

    @FXML
    void confirmAutomationButtonPressed(ActionEvent event) {

    }

}
