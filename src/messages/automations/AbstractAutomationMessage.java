//-----------------------------------------------------------------
// AbstractAutomationMessage.java
// Group 2
// Description: This class represents an abstract automation message that serves as a blueprint for specific automation events.
// Created By:
// Edited By:
// Approved By: Braydon, Francisco, Liam
// Variables:
// - private int deviceID: Represents the unique identifier of the device involved in the automation.
// - private Date date: Indicates the timestamp or scheduled time for the automation event.
// Error Handling:
//
//
//
//-----------------------------------------------------------------

package messages.automations;

import messages.AbstractMessage;

import java.util.Date;

public class AbstractAutomationMessage extends AbstractMessage {

    private int deviceID, deviceType;
    private Date date;

    public AbstractAutomationMessage(int deviceID, Date date, int deviceType) {
        super(4);
        this.deviceID = deviceID;
        this.date = date;
        this.deviceType = deviceType;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public Date getDate() {
        return date;
    }

    public int getDeviceType() {
        return deviceType;
    }

}
