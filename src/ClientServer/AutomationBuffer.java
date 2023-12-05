package ClientServer;

import GUI.Control.AutomationMenuController;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import messages.automations.LightAutomationMessage;

import java.util.Date;

public class AutomationBuffer {

    private static SmartHomeClient client;
    private static AutomationMenuController automationMenuController;

    private static int deviceID;
    private static String deviceName;

    // light specific automation variables
    private static boolean lightStatus;
    private static ToggleGroup lightToggleGroup;
    private static int brightness;
    private static Slider brightnessSlider = new Slider();
    private static String colour;
    private static ColorPicker colourPicker = new ColorPicker();

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

    public static void createLockAutomation(int deviceID, String deviceName, boolean lockStatus) {
        AutomationBuffer.deviceID = deviceID;
        AutomationBuffer.deviceName = deviceName;
        AutomationBuffer.lightStatus = lockStatus;

        automationMenuController.setTitle(deviceName, 1);
        automationMenuController.addLockActions();
    }

    public static void addSever(SmartHomeClient client){
        AutomationBuffer.client = client;
    }

    public static void addAutomationMenuController(AutomationMenuController amc){
        AutomationBuffer.automationMenuController = amc;
    }
}
