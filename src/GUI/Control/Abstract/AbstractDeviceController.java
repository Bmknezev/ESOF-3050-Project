package GUI.Control.Abstract;

import ClientServer.SmartHomeClient;

public abstract class AbstractDeviceController {
    protected SmartHomeClient client;

    public abstract void update(String[] s);

    public void addServer(SmartHomeClient s) {
        client = s;
    }

}
