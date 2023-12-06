package ClientServer;

import GUI.Control.AutomationMenuController;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import messages.automations.*;

import java.util.Date;

public class AutomationBuffer {

    private static SmartHomeClient client;
    private static AutomationMenuController automationMenuController;

    private static int deviceID;
    private static String deviceName;

    private static int timer, pin;
    private static TextField timerTextField, pinTextField;

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

    // smart thermostat specific variables
    private static float temperature;
    private static TextField temperatureTextField;

    // smart coffee machine specific variables
    private static int size, strength, temperatureCategory;
    private static ToggleGroup sizeToggleGroup, strengthToggleGroup, temperatureToggleGroup;

    // smart garage door opener specific variables
    private static boolean doorStatus;
    private static ToggleGroup doorToggleGroup;


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

        System.out.println("device id: " + deviceID + ", colour: " + colour + ", brightness: " + brightness + ", light status: " + lightStatus + ", date: " + date);
        LightAutomationMessage lam = new LightAutomationMessage(deviceID, colour, brightness, lightStatus, date);
        client.UpdateServer(lam);
    }

    public static void createLockAutomation(int deviceID, String deviceName, int pin) {
        AutomationBuffer.deviceID = deviceID;
        AutomationBuffer.deviceName = deviceName;
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
            default:
                lockStatus = true;
        }

        timer = timerTextField.getText().equals("") ? 0 : Integer.parseInt(timerTextField.getText());

        System.out.println("device id: " + deviceID + ", lock status: " + lockStatus + ", timer: " + timer + ", pin: " + pinTextField.getText() + ", date: " + date);
        if(pinTextField.getText().equals(Integer.toString(pin))){
            LockAutomationMessage lam = new LockAutomationMessage(deviceID, lockStatus, timer, pin, date);
            client.UpdateServer(lam);
        }
    }

    public static void createThermostatAutomation(int deviceID, String deviceName, float temperature) {
        AutomationBuffer.deviceID = deviceID;
        AutomationBuffer.deviceName = deviceName;
        AutomationBuffer.temperature = temperature;

        temperatureTextField = new TextField();
        temperatureTextField.setPromptText("Enter temperature in Celcius");

        automationMenuController.setTitle(deviceName, 2);
        automationMenuController.addThermostatActions(temperatureTextField);
    }
    public static void confirmThermostatAutomation(Date date){
        temperature = temperatureTextField.getText().equals("") ? temperature : Integer.parseInt(temperatureTextField.getText());

        System.out.println("device id: " + deviceID + ", temperature: " + temperature + ", date: " + date);
        ThermostatAutomationMessage tam = new ThermostatAutomationMessage(deviceID, temperature, date);
        client.UpdateServer(tam);
    }

    public static void createCoffeeMachineAutomation(int deviceID, String deviceName){
        AutomationBuffer.deviceID = deviceID;
        AutomationBuffer.deviceName = deviceName;
        size = 1;
        strength = 1;
        temperatureCategory = 1;

        sizeToggleGroup = new ToggleGroup();

        strengthToggleGroup = new ToggleGroup();

        temperatureToggleGroup = new ToggleGroup();

        automationMenuController.setTitle(deviceName, 3);
        automationMenuController.addCoffeeMakerActions(sizeToggleGroup, strengthToggleGroup, temperatureToggleGroup);
    }
    public static void confirmCoffeeMachineAutomation(Date date){
        size = (int)sizeToggleGroup.getSelectedToggle().getUserData();

        strength = (int)strengthToggleGroup.getSelectedToggle().getUserData();

        temperatureCategory = (int)temperatureToggleGroup.getSelectedToggle().getUserData();

        System.out.println("device id: " + deviceID + ", size: " + size + ", strength: " + strength + ", temperature category: " + temperatureCategory + ", date: " + date);
        CoffeeAutomationMessage cam = new CoffeeAutomationMessage(deviceID, date);
        client.UpdateServer(cam);
    }

    public static void createGarageDoorAutomation(int deviceID, String deviceName, int pin){
        AutomationBuffer.deviceID = deviceID;
        AutomationBuffer.deviceName = deviceName;
        AutomationBuffer.doorStatus = doorStatus;
        AutomationBuffer.timer = 0;
        AutomationBuffer.pin = pin;

        doorToggleGroup = new ToggleGroup();

        timerTextField = new TextField();
        timerTextField.setPromptText("Enter time in seconds");

        pinTextField = new TextField();
        pinTextField.setPromptText("Enter pin to make changes");

        automationMenuController.setTitle(deviceName, 4);
        automationMenuController.addGarageDoorActions(doorToggleGroup, timerTextField, pinTextField);
    }
    public static void confirmGarageDoorAutomation(Date date){
        switch ((int)doorToggleGroup.getSelectedToggle().getUserData()){
            case (1):
                doorStatus = false;
                break;
            case(2):
                doorStatus = true;
                break;
            default:
                doorStatus = false;
        }

        timer = timerTextField.getText().equals("") ? 0 : Integer.parseInt(timerTextField.getText());

        System.out.println("device id: " + deviceID + ", door status: " + doorStatus + ", timer: " + timer + ", pin: " + pinTextField.getText() + ", date: " + date);
        if(pinTextField.getText().equals(Integer.toString(pin))){
            GarageDoorAutomationMessage gdam = new GarageDoorAutomationMessage(deviceID, doorStatus, timer, pin, date);
            client.UpdateServer(gdam);
        }
    }

    public static void addSever(SmartHomeClient client){
        AutomationBuffer.client = client;
    }

    public static void addAutomationMenuController(AutomationMenuController amc){
        AutomationBuffer.automationMenuController = amc;
    }
}
