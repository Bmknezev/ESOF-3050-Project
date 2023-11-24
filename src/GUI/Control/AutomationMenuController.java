package GUI.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class AutomationMenuController {

    @FXML
    private Label smartDeviceNameLabel;

    @FXML
    private Label smartDeviceTypeLabel;

    @FXML
    private TextField repeatFrequencyTextField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ToggleGroup repeatSelection;

    @FXML
    private ToggleGroup repeatFrequencySelection;

    @FXML
    private ToggleGroup endDateSelection;

    @FXML
    private Button backButton;

    @FXML
    private Button confirmAutomationButton;

    @FXML
    private VBox smartDeviceActionsVBox;

    @FXML
    void backButtonPressed(ActionEvent event) {

    }

    @FXML
    void confirmAutomationButtonPressed(ActionEvent event) {

    }

}
