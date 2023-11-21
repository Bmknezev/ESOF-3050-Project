package messages.client;

public interface Listable {

    public abstract String getName();
        // username - user/admin
        // device name - smart devices

    public abstract String getCategory();
        // user/admin - user/admin
        // device type - smart device

    public abstract int getSceneType();
        // -1 - user/admin
        // 0 - smart light
        // 1 - smart lock
        // 2 - smart thermostat
        // 3 - smart coffee machine
        // 4 - smart garage door
        // 5 - smart smoke detector

    public abstract int getID();
}
