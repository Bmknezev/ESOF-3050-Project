package messages;

public class NewDeviceMessage extends AbstractMessage{
    //request a new device
    private String deviceName;
    private int deviceID;
    private String deviceType;

    public NewDeviceMessage(int id, String name, String deviceType){
        //constructor used by server
        super(2);
        this.deviceName = name;
        this.deviceID = id;
        this.deviceType = deviceType;
    }

    public NewDeviceMessage(int id){
        //constructor used by client
        super(2);
        this.deviceID = id;
        this.deviceName = "";
        this.deviceType = "";
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
