package ClientServer;

import GUI.Control.AutomationMenuController;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import messages.automations.LightAutomationMessage;
import messages.automations.LockAutomationMessage;

import java.util.Date;

public class AutomationBuffer {

    private static SmartHomeClient client;
    private static AutomationMenuController automationMenuController;

    private static int deviceID;
    private static String deviceName;

    // smart light specific automation variables
    private static boolean lightStatus;
    private static ToggleGroup lightToggleGroup;

    private static int brightness;
    private static Slider brightnessSlider = new Slider();

    private static String colour;
    private static ColorPicker colourPicker = new ColorPicker();

    // smart lock specific variables

    private static boolean lockStatus;
    private static ToggleGroup lockToggleGroup;

    private static int timer, pin;
    private static TextField timerTextField, pinTextField;

    public static void createLightAutomation(int deviceID, String deviceName, String colour, int brightness, boolean lightStatus) {
        AutomationBuffer.deviceID = deviceID;
        AutomationBuffer.deviceName = deviceName;
        AutomationBuffer.colour = colour;
        AutomationBuffer.brightness = brightness;
        AutomationBuffer.lightStatus = lightStatus;

        lightToggleGroup = new ToggleGroup();

        brightnessSlider = new Slider();
        brightnessSlider.setValue(brightness);

        colourPicker = new ColorPicker();
        colourPicker.setValue(javafx.scene.paint.Color.valueOf(colour));

        automationMenuController.setTitle(deviceName, 0);
        automationMenuController.addLightActions(lightToggleGroup, brightnessSlider, colourPicker);

    }

    public static void confirmLightAutomation(Date date){
        switch ((int)lightToggleGroup.getSelectedToggle().getUserData()){
            case (1):
                lightStatus = true;
                break;
            case(2):
                lightStatus = false;
                break;
            case(3):
                lightStatus = !lightStatus;
                break;
        }

        if (!brightnessSlider.isDisable())
            brightness = (int) brightnessSlider.getValue();

        if (!colourPicker.isDisable())
            colour = colourPicker.getValue().toString().substring(2, 8);

        LightAutomationMessage lam = new LightAutomationMessage(deviceID, colour, brightness, lightStatus, date);
        client.UpdateServer(lam);
    }

    public static void createLockAutomation(int deviceID, String deviceName, boolean lockStatus, int pin) {
        AutomationBuffer.deviceID = deviceID;
        AutomationBuffer.deviceName = deviceName;
        AutomationBuffer.lockStatus = lockStatus;
        AutomationBuffer.timer = 0;
        AutomationBuffer.pin = pin;

        lockToggleGroup = new ToggleGroup();

        timerTextField = new TextField();
        timerTextField.setPromptText("Enter time in seconds");

        pinTextField = new TextField();
        pinTextField.setPromptText("Enter pin to make changes");

        automationMenuController.setTitle(deviceName, 1);
        automationMenuController.addLockActions(lockToggleGroup, timerTextField, pinTextField);
    }

    public static void confirmLockAutomation(Date date){

        switch ((int)lockToggleGroup.getSelectedToggle().getUserData()){
            case (1):
                lockStatus = true;
                break;
            case(2):
                lockStatus = false;
                break;
        }

        timer = timerTextField.getText().equals("") ? 0 : Integer.parseInt(timerTextField.getText());

        if (pinTextField.getText().equals(Integer.toString(pin))){
            LockAutomationMessage lam = new LockAutomationMessage(deviceID, lockStatus, timer, pin, date);
            client.UpdateServer(lam);
        }
    }

    public static void addSever(SmartHomeClient client){
        AutomationBuffer.client = client;
    }

    public static void addAutomationMenuController(AutomationMenuController amc){
        AutomationBuffer.automationMenuController = amc;
    }
}
