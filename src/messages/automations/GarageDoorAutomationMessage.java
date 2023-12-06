//-----------------------------------------------------------------
// GarageDoorAutomationMessage.java
// Group 2
// Description: Represents a message for garage door automation in a system.
//              Extends AbstractAutomationMessage.
// Created By: Braydon
// Edited By: Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - doorStatus: boolean - Represents the status of the garage door (open or closed).
//-----------------------------------------------------------------

package messages.automations;

import java.util.Date;

public class GarageDoorAutomationMessage extends AbstractAutomationMessage{
    private boolean doorStatus;
    int timer, pin;

    public GarageDoorAutomationMessage(int deviceID, boolean doorStatus, int timer, int pin, Date date) {
        super(deviceID, date, 4);
        this.doorStatus = doorStatus;
        this.timer = timer;
        this.pin = pin;
    }

    public boolean getDoorStatus() {
        return doorStatus;
    }

    public int getTimer() {
        return timer;
    }

    public int getPIN() {
        return pin;
    }
}
