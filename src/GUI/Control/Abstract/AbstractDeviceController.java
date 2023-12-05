package GUI.Control.Abstract;

import ClientServer.SmartHomeClient;
import GUI.Control.AutomationMenuController;
import javafx.scene.Scene;
import messages.PinMessage;

public abstract class AbstractDeviceController {
    protected SmartHomeClient client;
    protected static AutomationMenuController automationMenuController;
    protected static Scene automationScene;

    //public abstract void update(AbstractDeviceMessage msg);

    public void addServer(SmartHomeClient s) {
        client = s;
    }

    public static void addAutomationMenu(AutomationMenuController a, Scene s) {
        automationMenuController = a;
        automationScene = s;
    }

    public void response(PinMessage msg) {
    }

    public void setPIN(int newPin) {
    }

    public String getDeviceName() {
        return "";
    }

    public String getDeviceType() {
        return "";
    }

    public int getDeviceID() {
        return -1;
    }

    public Scene getScene(){
        return null;
    }
}
