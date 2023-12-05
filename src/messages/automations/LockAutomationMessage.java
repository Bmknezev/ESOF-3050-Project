//-----------------------------------------------------------------
// LockAutomationMessage.java
// Group 2
// Description: Represents a message for lock automation in a system.
//              Extends AbstractAutomationMessage.
// Created By: Braydon
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - lockStatus: boolean - Represents the status of the lock (locked or unlocked).
//   - timer: int - Represents a timer associated with the lock.
//   - pin: int - Represents the Personal Identification Number (PIN) used with the lock.
//-----------------------------------------------------------------

package messages.automations;

import java.time.LocalDate;
import java.util.Date;

public class LockAutomationMessage extends AbstractAutomationMessage{
    private boolean lockStatus;
    private int timer;
    private int pin;

    public LockAutomationMessage(int deviceID, boolean lockStatus, int timer, int pin, Date date) {
        super(deviceID, date, 1);
        this.lockStatus = lockStatus;
        this.timer = timer;
        this.pin = pin;
    }

    public boolean getLockStatus() {
        return lockStatus;
    }

    public int getTimer() {
        return timer;
    }

    public int getPIN() {
        return pin;
    }
}
