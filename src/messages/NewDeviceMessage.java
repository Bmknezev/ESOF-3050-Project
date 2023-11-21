package messages;

import messages.client.Listable;

public class NewDeviceMessage extends AbstractMessage implements Listable {
    //request a new device
    private String deviceName;
    private int deviceID, deviceTypeNumber;
    private String deviceType;

    public NewDeviceMessage(int id, String name, String deviceType) {
        //constructor used by server
        super(2);
        this.deviceName = name;
        this.deviceID = id;
        this.deviceType = deviceType;

        determineDeviceTypeNumber(deviceType);
    }

    private void determineDeviceTypeNumber(String deviceType) {
        switch (deviceType) {
            case "Smart Light":
                deviceTypeNumber = 0;
                break;
            case "Smart Lock":
                deviceTypeNumber = 1;
                break;
            case "Smart Thermostat":
                deviceTypeNumber = 2;
                break;
            case "Smart Coffee Machine":
                deviceTypeNumber = 3;
                break;
            case "Smart Garage Door":
                deviceTypeNumber = 4;
                break;
            case "Smart Smoke Detector":
                deviceTypeNumber = 5;
                break;
            default:
                System.out.println("Error: Device type not found");
                break;
        }
    }

    public NewDeviceMessage(int id) {
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

    public int getDeviceTypeNumber() {
        return deviceTypeNumber;
    }

    public String getName() {
        return deviceName;
    }


    public String getDeviceType() {
        return deviceType;
    }

    @Override
    public String getNameListable() {
        return deviceName;
    }

    @Override
    public String getCategoryListable() {
        return deviceType;
    }
}
