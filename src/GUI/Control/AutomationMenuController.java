//-----------------------------------------------------------------
// AutomationMenuController.java
// Group 2
// Description: This class represents the controller for the automation menu in the Smart Home system.
//              It manages user interactions related to setting up device automation, such as specifying
//              repeat frequency, start and end dates, and handling button presses for confirmations and
//              navigation.
// Created By:
// Edited By:
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

import ClientServer.SmartDeviceIndex;
import GUI.Control.Abstract.AbstractDeviceController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import messages.AbstractDeviceMessage;
import messages.automations.LightAutomationMessage;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class AutomationMenuController extends AbstractDeviceController {
    @FXML
    public TextField hourTextField;
    @FXML
    public TextField minuteTextField;
    @FXML
    private Label smartDeviceNameLabel;

    @FXML
    private Label smartDeviceTypeLabel;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private Button backButton;

    @FXML
    private Button confirmAutomationButton;

    @FXML
    private VBox smartDeviceActionsVBox;

    AbstractDeviceController device;
    SmartLightMenuController light = (SmartLightMenuController) device;
    private Scene previous;
    private Date date;
    private int deviceTypeNumber, brightness;
    private boolean lightStatus;
    private String colour;

    public void update(AbstractDeviceController a){
        smartDeviceActionsVBox.getChildren().clear();
        previous = a.getScene();
        smartDeviceNameLabel.setText(a.getDeviceName());
        smartDeviceTypeLabel.setText(a.getDeviceType());
        deviceTypeNumber = SmartDeviceIndex.getDeviceTypeNumber(a.getDeviceType());

        addAutomatableActions();
    }

    private void addAutomatableActions(){
        switch(deviceTypeNumber){
            case 0:
                addLightActions();
                break;
            case 1:
                //addLockActions();
                break;
            case 2:
                //addThermostatActions();
                break;
            case 3:
                //addCoffeeMachineActions();
                break;
            case 4:
                //addGarageDoorActions();
                break;
            case 5:
                //AddSmokeDetectorActions();
                break;
            default:
                // error
                break;
        }
    }

    private void addLightActions(){
            // this creates the light toggle actions
        HBox lightToggleHBox = new HBox();
        lightToggleHBox.setSpacing(10);
        lightToggleHBox.setAlignment(javafx.geometry.Pos.CENTER);

        ToggleGroup LightToggleGroup = new ToggleGroup();
        RadioButton onRadioButton = new RadioButton("On");
        RadioButton offRadioButton = new RadioButton("Off");
        RadioButton toggleRadioButton = new RadioButton("Toggle");
        RadioButton noChangeRadioButton = new RadioButton("No Change");

        onRadioButton.setToggleGroup(LightToggleGroup);
        offRadioButton.setToggleGroup(LightToggleGroup);
        toggleRadioButton.setToggleGroup(LightToggleGroup);
        noChangeRadioButton.setToggleGroup(LightToggleGroup);
        noChangeRadioButton.setSelected(true);

        onRadioButton.setOnAction(event -> {
            if (onRadioButton.isSelected()){
                lightStatus = true;
            }
        });

        offRadioButton.setOnAction(event -> {
            if (offRadioButton.isSelected()){
                lightStatus = false;
            }
        });

        toggleRadioButton.setOnAction(event -> {
            if (toggleRadioButton.isSelected()){
                lightStatus = !light.getLightStatus();
            }
        });

        noChangeRadioButton.setOnAction(event -> {
            if (noChangeRadioButton.isSelected()){
                lightStatus = light.getLightStatus();
            }
        });

        lightToggleHBox.getChildren().addAll(onRadioButton, offRadioButton, toggleRadioButton, noChangeRadioButton);

            // this creates the brightness slider actions
        HBox brightnessSliderHBox = new HBox();
        brightnessSliderHBox.setSpacing(10);
        brightnessSliderHBox.setAlignment(javafx.geometry.Pos.CENTER);

        RadioButton brightnessRadioButton = new RadioButton("Change Brightness: ");

        Slider brightnessSlider = new Slider();
        brightnessSlider.setDisable(true);
        brightnessSlider.setValue(50);

        Label brightnessLabel = new Label("50%");

        brightnessRadioButton.setOnAction(event -> {
            if (brightnessRadioButton.isSelected()){
                brightnessSlider.setDisable(false);
                brightness = light.getBrightness();
            }
            else{
                brightnessSlider.setDisable(true);
            }
        });

        brightnessSlider.setOnMouseDragged(event -> {
            brightnessLabel.setText((int)brightnessSlider.getValue() + "%");
            brightness = (int)brightnessSlider.getValue();
        });

        brightnessSliderHBox.getChildren().addAll(brightnessRadioButton, brightnessSlider, brightnessLabel);

            // this creates the light colour actions
        HBox lightColourHBox = new HBox();
        lightColourHBox.setSpacing(10);
        lightColourHBox.setAlignment(javafx.geometry.Pos.CENTER);

        RadioButton colourRadioButton = new RadioButton("Change Colour: ");

        ColorPicker colourPicker = new ColorPicker();
        colourPicker.setDisable(true);

        colourRadioButton.setOnAction(event -> {
            if (colourRadioButton.isSelected()){
                colourPicker.setDisable(false);
                colour = light.getColour();
            }
            else{
                colourPicker.setDisable(true);
            }
        });

        colourPicker.setOnAction(event -> {
            colour = colourPicker.getValue().toString().substring(2, 8);
        });

        lightColourHBox.getChildren().addAll(colourRadioButton, colourPicker);

        smartDeviceActionsVBox.getChildren().addAll(lightToggleHBox, brightnessSliderHBox, lightColourHBox);
    }


    @FXML
    void confirmAutomationButtonPressed(ActionEvent event) {
        date = addTimeToDate(Date.from(startDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Integer.parseInt(minuteTextField.getText()), Integer.parseInt(hourTextField.getText()));
        //smartDeviceNameLabel.setText(date.toString());
        switch(deviceTypeNumber){
            case 0:
                confirmLightAutomation();
                break;
            case 1:
                //confirmLockAutomation();
                break;
            case 2:
                //confirmThermostatAutomation();
                break;
            case 3:
                //confirmCoffeeMachineAutomation();
                break;
            case 4:
                //confirmGarageDoorAutomation();
                break;
            case 5:
                //confirmSmokeDetectorAutomation();
                break;
            default:
                // error
                break;
        }
    }

    private void confirmLightAutomation(){
        LightAutomationMessage lam = new LightAutomationMessage(light.getDeviceID(), colour, brightness, lightStatus, date);
        client.UpdateServer(lam);
    }

    @FXML
    void backButtonPressed(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previous);
    }

    public Date addTimeToDate(Date date, int hours, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
}
