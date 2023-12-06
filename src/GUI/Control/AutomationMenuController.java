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

import ClientServer.SmartDeviceIndex;
import ClientServer.SmartHomeClient;
import GUI.Control.Abstract.AbstractDeviceController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import messages.AbstractDeviceMessage;
import messages.automations.LightAutomationMessage;
import ClientServer.AutomationBuffer;

import java.time.LocalDate;
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
    private Scene previous;
    private Date date;

    private int deviceTypeNumber;

    public void addServer(SmartHomeClient s) {
        client = s;
    }

    public void setPrevious(Scene s){
        previous = s;
    }

    public void setTitle(String name, int deviceTypeNumber ){
        smartDeviceNameLabel.setText(name);
        smartDeviceTypeLabel.setText(SmartDeviceIndex.getDeviceType(deviceTypeNumber));

        this.deviceTypeNumber = deviceTypeNumber;
        smartDeviceActionsVBox.getChildren().clear();
    }

    public void addLightActions(ToggleGroup lightToggleGroup, Slider brightnessSlider, ColorPicker colourPicker){
        Platform.runLater(() -> {
                    // this creates the light toggle actions
            HBox lightToggleHBox = new HBox();
            lightToggleHBox.setSpacing(10);
            lightToggleHBox.setAlignment(javafx.geometry.Pos.CENTER);

            RadioButton onRadioButton = new RadioButton("On");
            onRadioButton.setUserData(1);
            onRadioButton.setToggleGroup(lightToggleGroup);

            RadioButton offRadioButton = new RadioButton("Off");
            offRadioButton.setUserData(2);
            offRadioButton.setToggleGroup(lightToggleGroup);

            RadioButton toggleRadioButton = new RadioButton("Toggle");
            toggleRadioButton.setUserData(3);
            toggleRadioButton.setToggleGroup(lightToggleGroup);

            RadioButton noChangeRadioButton = new RadioButton("No Change");
            noChangeRadioButton.setUserData(4);
            noChangeRadioButton.setToggleGroup(lightToggleGroup);
            noChangeRadioButton.setSelected(true);

            lightToggleHBox.getChildren().addAll(onRadioButton, offRadioButton, toggleRadioButton, noChangeRadioButton);

                // this creates the brightness slider actions
            HBox brightnessSliderHBox = new HBox();
            brightnessSliderHBox.setSpacing(10);
            brightnessSliderHBox.setAlignment(javafx.geometry.Pos.CENTER);

            RadioButton brightnessRadioButton = new RadioButton("Change Brightness: ");

            brightnessSlider.setDisable(true);

            Label brightnessLabel = new Label("50%");

            brightnessRadioButton.setOnAction(event -> {
                if (brightnessRadioButton.isSelected()){
                    brightnessSlider.setDisable(false);
                }
                else{
                    brightnessSlider.setDisable(true);
                }
            });

            brightnessSlider.setOnMouseDragged(event -> {
                brightnessLabel.setText((int)brightnessSlider.getValue() + "%");
            });

            brightnessSliderHBox.getChildren().addAll(brightnessRadioButton, brightnessSlider, brightnessLabel);

                // this creates the light colour actions
            HBox lightColourHBox = new HBox();
            lightColourHBox.setSpacing(10);
            lightColourHBox.setAlignment(javafx.geometry.Pos.CENTER);

            RadioButton colourRadioButton = new RadioButton("Change Colour: ");

            colourPicker.setDisable(true);

            colourRadioButton.setOnAction(event -> {
                if (colourRadioButton.isSelected()){
                    colourPicker.setDisable(false);
                }
                else{
                    colourPicker.setDisable(true);
                }
            });

            lightColourHBox.getChildren().addAll(colourRadioButton, colourPicker);

            smartDeviceActionsVBox.getChildren().addAll(lightToggleHBox, brightnessSliderHBox, lightColourHBox);
        });
    }

    public void addLockActions(ToggleGroup lockToggleGroup, TextField timerTextField, TextField pinTextField){
        Platform.runLater(() -> {
                // this creates the lock toggle actions
            HBox lockToggleHBox = new HBox();
            lockToggleHBox.setSpacing(10);
            lockToggleHBox.setAlignment(javafx.geometry.Pos.CENTER);

            RadioButton lockRadioButton = new RadioButton("Lock");
            lockRadioButton.setUserData(1);
            lockRadioButton.setToggleGroup(lockToggleGroup);
            lockRadioButton.setSelected(true);

            RadioButton unlockRadioButton = new RadioButton("Unlock");
            unlockRadioButton.setUserData(2);
            unlockRadioButton.setToggleGroup(lockToggleGroup);

            lockToggleHBox.getChildren().addAll(lockRadioButton, unlockRadioButton);

                // this creates the timer actions
            HBox timerHBox = new HBox();
            timerHBox.setSpacing(10);
            timerHBox.setAlignment(javafx.geometry.Pos.CENTER);

            RadioButton timerRadioButton = new RadioButton("Set Timer: ");

            timerTextField.setDisable(true);

            timerRadioButton.setOnAction(event -> {
                if (timerRadioButton.isSelected()){
                    timerTextField.setDisable(false);
                }
                else{
                    timerTextField.setDisable(true);
                }
            });

            timerHBox.getChildren().addAll(timerRadioButton, timerTextField);

                // this creates the pin actions
            HBox pinHBox = new HBox();
            pinHBox.setSpacing(10);
            pinHBox.setAlignment(javafx.geometry.Pos.CENTER);

            pinHBox.getChildren().addAll(pinTextField);

            smartDeviceActionsVBox.getChildren().addAll(lockToggleHBox, timerHBox, pinHBox);
        });
    }

    public void addThermostatActions(TextField temperatureTextField) {
        Platform.runLater(() -> {
                // this creates the temperature toggle actions

            HBox temperatureHBox = new HBox();
            temperatureHBox.setSpacing(10);
            temperatureHBox.setAlignment(javafx.geometry.Pos.CENTER);

            temperatureHBox.getChildren().addAll(temperatureTextField);

            smartDeviceActionsVBox.getChildren().addAll(temperatureHBox);
        });
    }

    public void addCoffeeMakerActions(ToggleGroup sizeToggleGroup, ToggleGroup strengthToggleGroup, ToggleGroup temperatureToggleGroup){
        Platform.runLater(() -> {
                // this creates the size toggle actions
            HBox sizeToggleHBox = new HBox();
            sizeToggleHBox.setSpacing(10);
            sizeToggleHBox.setAlignment(javafx.geometry.Pos.CENTER);

            RadioButton smallRadioButton = new RadioButton("Small");
            smallRadioButton.setUserData(0);
            smallRadioButton.setToggleGroup(sizeToggleGroup);

            RadioButton mediumRadioButton = new RadioButton("Medium");
            mediumRadioButton.setUserData(1);
            mediumRadioButton.setToggleGroup(sizeToggleGroup);
            mediumRadioButton.setSelected(true);

            RadioButton largeRadioButton = new RadioButton("Large");
            largeRadioButton.setUserData(2);
            largeRadioButton.setToggleGroup(sizeToggleGroup);

            sizeToggleHBox.getChildren().addAll(smallRadioButton, mediumRadioButton, largeRadioButton);

                // this creates the strength toggle actions
            HBox strengthToggleHBox = new HBox();
            strengthToggleHBox.setSpacing(10);
            strengthToggleHBox.setAlignment(javafx.geometry.Pos.CENTER);

            RadioButton weakRadioButton = new RadioButton("Weak");
            weakRadioButton.setUserData(0);
            weakRadioButton.setToggleGroup(strengthToggleGroup);

            RadioButton mediumStrengthRadioButton = new RadioButton("Medium Strength");
            mediumStrengthRadioButton.setUserData(1);
            mediumStrengthRadioButton.setToggleGroup(strengthToggleGroup);
            mediumStrengthRadioButton.setSelected(true);

            RadioButton strongRadioButton = new RadioButton("Strong");
            strongRadioButton.setUserData(2);
            strongRadioButton.setToggleGroup(strengthToggleGroup);

            strengthToggleHBox.getChildren().addAll(weakRadioButton, mediumStrengthRadioButton, strongRadioButton);

                // this creates the temperature toggle actions
            HBox temperatureToggleHBox = new HBox();
            temperatureToggleHBox.setSpacing(10);
            temperatureToggleHBox.setAlignment(javafx.geometry.Pos.CENTER);

            RadioButton hotRadioButton = new RadioButton("Hot");
            hotRadioButton.setUserData(0);
            hotRadioButton.setToggleGroup(temperatureToggleGroup);

            RadioButton mediumTemperatureRadioButton = new RadioButton("Medium Temperature");
            mediumTemperatureRadioButton.setUserData(1);
            mediumTemperatureRadioButton.setToggleGroup(temperatureToggleGroup);
            mediumTemperatureRadioButton.setSelected(true);

            RadioButton coldRadioButton = new RadioButton("Cold");
            coldRadioButton.setUserData(2);
            coldRadioButton.setToggleGroup(temperatureToggleGroup);

            temperatureToggleHBox.getChildren().addAll(hotRadioButton, mediumTemperatureRadioButton, coldRadioButton);

            smartDeviceActionsVBox.getChildren().addAll(sizeToggleHBox, strengthToggleHBox, temperatureToggleHBox);
        });
    }

    public void addGarageDoorActions(ToggleGroup doorToggleGroup, TextField timerTextField, TextField pinTextField) {
        Platform.runLater(() -> {
            // this creates the lock toggle actions
            HBox doorToggleHBox = new HBox();
            doorToggleHBox.setSpacing(10);
            doorToggleHBox.setAlignment(javafx.geometry.Pos.CENTER);

            RadioButton closeRadioButton = new RadioButton("Close");
            closeRadioButton.setUserData(1);
            closeRadioButton.setToggleGroup(doorToggleGroup);
            closeRadioButton.setSelected(true);

            RadioButton openRadioButton = new RadioButton("Open");
            openRadioButton.setUserData(2);
            openRadioButton.setToggleGroup(doorToggleGroup);

            doorToggleHBox.getChildren().addAll(closeRadioButton, openRadioButton);

            // this creates the timer actions
            HBox timerHBox = new HBox();
            timerHBox.setSpacing(10);
            timerHBox.setAlignment(javafx.geometry.Pos.CENTER);

            RadioButton timerRadioButton = new RadioButton("Set Timer: ");

            timerTextField.setDisable(true);

            timerRadioButton.setOnAction(event -> {
                if (timerRadioButton.isSelected()){
                    timerTextField.setDisable(false);
                }
                else{
                    timerTextField.setDisable(true);
                }
            });

            timerHBox.getChildren().addAll(timerRadioButton, timerTextField);

            // this creates the pin actions
            HBox pinHBox = new HBox();
            pinHBox.setSpacing(10);
            pinHBox.setAlignment(javafx.geometry.Pos.CENTER);

            pinHBox.getChildren().addAll(pinTextField);

            smartDeviceActionsVBox.getChildren().addAll(doorToggleHBox, timerHBox, pinHBox);
        });
    }
    @FXML
    void confirmAutomationButtonPressed(ActionEvent event) {
        LocalDate startDate;
        if (startDatePicker.getValue() != null){
            startDate = startDatePicker.getValue();
        }
        else{
            startDate = LocalDate.now();
        }
        int hours = hourTextField.getText().equals("") ? 0 : Integer.parseInt(hourTextField.getText());
        int minutes = minuteTextField.getText().equals("") ? 0 : Integer.parseInt(minuteTextField.getText());

        date = addTimeToDate(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), hours, minutes);

        switch(deviceTypeNumber){
            case 0:
                AutomationBuffer.confirmLightAutomation(date);
                break;
            case 1:
                AutomationBuffer.confirmLockAutomation(date);
                break;
            case 2:
                AutomationBuffer.confirmThermostatAutomation(date);
                break;
            case 3:
                AutomationBuffer.confirmCoffeeMachineAutomation(date);
                break;
            case 4:
                AutomationBuffer.confirmGarageDoorAutomation(date);
                break;
            default:
                // error
                break;
        }
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
