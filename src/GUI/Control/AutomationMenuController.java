//-----------------------------------------------------------------
// AutomationMenuController.java
// Group 2
// Description: This class represents the controller for the automation menu in the Smart Home system.
//              It manages user interactions related to setting up device automation, such as specifying
//              repeat frequency, start and end dates, and handling button presses for confirmations and
//              navigation.
// Created By: Francisco
// Edited By:Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - smartDeviceNameLabel: Label - Label displaying the smart device name
//   - smartDeviceTypeLabel: Label - Label displaying the smart device type
//   - repeatFrequencyTextField: TextField - TextField for specifying repeat frequency
//   - startDatePicker: DatePicker - DatePicker for selecting the start date
//   - endDatePicker: DatePicker - DatePicker for selecting the end date
//   - repeatSelection: ToggleGroup - ToggleGroup for handling repeat selection
//   - repeatFrequencySelection: ToggleGroup - ToggleGroup for selecting repeat frequency
//   - endDateSelection: ToggleGroup - ToggleGroup for selecting an end date
//   - backButton: Button - Button for navigating back
//   - confirmAutomationButton: Button - Button for confirming automation settings
//   - smartDeviceActionsVBox: VBox - VBox containing smart device actions
//   - yesRepeatRadioButton: RadioButton - RadioButton for enabling repeat options
//   - noRepeatRadioButton: RadioButton - RadioButton for disabling repeat options
//   - secondsRadioButton: RadioButton - RadioButton for seconds in repeat frequency
//   - minutesRadioButton: RadioButton - RadioButton for minutes in repeat frequency
//   - hoursRadioButton: RadioButton - RadioButton for hours in repeat frequency
//   - daysRadioButton: RadioButton - RadioButton for days in repeat frequency
//   - yesEndDateRadioButton: RadioButton - RadioButton for enabling an end date
//   - noEndDateRadioButton: RadioButton - RadioButton for disabling an end date
//   - disableableRadioButtons: RadioButton[] - Array containing radio buttons that can be disabled or enabled
//-----------------------------------------------------------------

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
