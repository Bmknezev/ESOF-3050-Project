package GUI.Control.Abstract;

import ClientServer.SmartHomeClient;
import messages.AbstractDeviceMessage;

public abstract class AbstractDeviceController {
    protected SmartHomeClient client;

    //public abstract void update(AbstractDeviceMessage msg);

    public void addServer(SmartHomeClient s) {
        client = s;
    }

}
