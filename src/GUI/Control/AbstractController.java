package GUI.Control;

import ClientServer.SmartHomeClient;

public abstract class AbstractController {
    protected SmartHomeClient client;

    public abstract void update(String[] s);

    public void addServer(SmartHomeClient s) {
        client = s;
    }

}
