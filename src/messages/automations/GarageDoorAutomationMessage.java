//-----------------------------------------------------------------
// GarageDoorAutomationMessage.java
// Group 2
// Description: Represents a message for garage door automation in a system.
//              Extends AbstractAutomationMessage.
// Created By: Braydon
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - doorStatus: boolean - Represents the status of the garage door (open or closed).
//-----------------------------------------------------------------

package messages.automations;

import java.time.LocalDate;
import java.util.Date;

public class GarageDoorAutomationMessage extends AbstractAutomationMessage{
    private boolean doorStatus;

    public GarageDoorAutomationMessage(int deviceID, boolean doorStatus, Date date) {
        super(deviceID, date, 4);
        this.doorStatus = doorStatus;
    }

    public boolean getDoorStatus() {
        return doorStatus;
    }
}
