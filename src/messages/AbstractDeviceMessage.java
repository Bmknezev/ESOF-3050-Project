//-----------------------------------------------------------------
// AbstractDeviceMessage.java
// Group 2
// Description: Abstract class representing a device message.
// Created By:
// Edited By:
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - deviceName: String - Name of the device.
//   - deviceID: int - ID of the device.
//
//-----------------------------------------------------------------

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
