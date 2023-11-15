package messages;

public abstract class AbstractDeviceMessage extends AbstractMessage{
    private String deviceName;
    private int deviceID;
    private boolean connectionStatus;
    private int battery;
    private boolean status;


    public AbstractDeviceMessage(boolean type, int id, String name, boolean connectionStatus, int battery, boolean status){
        super(type);
        this.deviceName = name;
        this.deviceID = id;
        this.connectionStatus = connectionStatus;
        this.battery = battery;
        this.status = status;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public boolean getConnectionStatus() {
        return connectionStatus;
    }

    public int getBattery() {
        return battery;
    }

    public boolean getStatus() {
        return status;
    }

    public String getName() {
        return deviceName;
    }
}
