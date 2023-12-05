//-----------------------------------------------------------------
// AbstractAutomationMessage.java
// Group 2
// Description: This class represents an abstract automation message that serves as a blueprint for specific automation events.
// Created By: Braydon
// Edited By: Francisco, Braydon
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

import java.time.LocalDate;
import java.util.Date;

public class AbstractAutomationMessage extends AbstractMessage {

    private int deviceID, deviceType;
    private LocalDate date;

    public AbstractAutomationMessage(int deviceID, LocalDate date, int deviceType) {
        super(4);
        this.deviceID = deviceID;
        this.date = date;
        this.deviceType = deviceType;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getDeviceType() {
        return deviceType;
    }

}
