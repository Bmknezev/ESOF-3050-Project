package GUI.Control;

import GUI.Control.Abstract.AbstractDeviceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import messages.AbstractDeviceMessage;

public class AutomationMenuController extends AbstractDeviceController {

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
    private RadioButton yesRepeatRadioButton;

    @FXML
    private RadioButton noRepeatRadioButton;

    @FXML
    private RadioButton secondsRadioButton;

    @FXML
    private RadioButton minutesRadioButton;

    @FXML
    private RadioButton hoursRadioButton;

    @FXML
    private RadioButton daysRadioButton;

    @FXML
    private RadioButton yesEndDateRadioButton;

    @FXML
    private RadioButton noEndDateRadioButton;

    private RadioButton disableableRadioButtons[] = {secondsRadioButton, minutesRadioButton, hoursRadioButton, daysRadioButton, yesEndDateRadioButton, noEndDateRadioButton};


    public void update(AbstractDeviceController a){
        smartDeviceNameLabel.setText(a.getDeviceName());
        smartDeviceTypeLabel.setText(a.getDeviceType());
        //smartDeviceActionsVBox.getChildren().addAll(a.getDeviceActions());
    }


    @FXML
    void confirmAutomationButtonPressed(ActionEvent event) {

    }

    @FXML
    void yesRepeatPressed(ActionEvent event) {
            // this enables all the radio buttons
        for(int i = 0; i < disableableRadioButtons.length; i++){
            disableableRadioButtons[i].setDisable(false);
        }
            // this enables the end date picker if the user has chosen to have an end date
        if (endDateSelection.getSelectedToggle() == yesEndDateRadioButton)
            endDatePicker.setDisable(false);
    }

    @FXML
    void noRepeatPressed(ActionEvent event) {
            // this disables all the radio buttons
        for(int i = 0; i < disableableRadioButtons.length; i++){
            disableableRadioButtons[i].setDisable(true);
        }
            // this disables the end date picker
        endDatePicker.setDisable(true);
    }

    @FXML
    void yesEndDatePressed(ActionEvent event) {
        endDatePicker.setDisable(false);
    }
    @FXML
    void noEndDatePressed(ActionEvent event) {
        endDatePicker.setDisable(true);
    }

    @FXML
    void backButtonPressed(ActionEvent event) {

    }
}
