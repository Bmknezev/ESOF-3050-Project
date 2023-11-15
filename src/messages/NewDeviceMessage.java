package messages;

public class NewDeviceMessage extends AbstractMessage{
    private String deviceName;
    private int deviceID;
    private String deviceType;

    public NewDeviceMessage(boolean type, int id, String name, String deviceType){
        super(type);
        this.deviceName = name;
        this.deviceID = id;
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public String getName() {
        return deviceName;
    }


    public String getDeviceType() {
        return deviceType;
    }
}
