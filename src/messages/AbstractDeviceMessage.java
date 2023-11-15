package messages;

public abstract class AbstractDeviceMessage extends AbstractMessage{
    private String deviceName;
    private int deviceID;

    public AbstractDeviceMessage(String name, int id){
        super(1);
        this.deviceName = name;
        this.deviceID = id;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public String getName() {
        return deviceName;
    }
}
