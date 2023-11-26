package GUI.Control.Abstract;

import ClientServer.SmartHomeClient;
import messages.PinMessage;

public abstract class AbstractDeviceController {
    protected SmartHomeClient client;

    //public abstract void update(AbstractDeviceMessage msg);

    public void addServer(SmartHomeClient s) {
        client = s;
    }

    public void response(PinMessage msg) {
    }

    public void setPIN(int newPin) {
    }
}
