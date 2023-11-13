package GUI.Control;


public abstract class AbstractController {
    protected SmartHomeClient client;

    public abstract void update(String[] s);

    public void addServer(SmartHomeClient s) {
        client = s;
    }
}
