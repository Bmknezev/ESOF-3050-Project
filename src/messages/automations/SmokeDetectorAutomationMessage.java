//-----------------------------------------------------------------
// SmokeDetectorAutomationMessage.java
// Group 2
// Description: Represents an automation message for a smoke detector in a system.
//              Extends AbstractAutomationMessage.
// Created By: Braydon
// Edited By:
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - alarm: boolean - Represents the alarm status of the smoke detector.
//-----------------------------------------------------------------

package messages.automations;

import java.util.Date;

public class SmokeDetectorAutomationMessage extends AbstractAutomationMessage{
    private boolean alarm;

    public SmokeDetectorAutomationMessage(int deviceID, boolean alarm, Date date) {
        super(deviceID, date, 5);
        this.alarm = alarm;
    }

    public boolean isAlarm() {
        return alarm;
    }

    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }
}
