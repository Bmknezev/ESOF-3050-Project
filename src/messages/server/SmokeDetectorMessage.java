//-----------------------------------------------------------------
// SmokeDetectorMessage.java
// Group 2
// Description: Represents a message related to smoke detectors.
// Created By: Braydon
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - lastTested: Date - Represents the date of the last test.
//   - testStatus: boolean - Indicates whether a test is needed (true) or not (false).
//   - alarmStatus: boolean - Represents the readiness of the alarm (true if ready, false if not).
//
//-----------------------------------------------------------------

package messages.server;

import messages.AbstractDeviceMessage;

import java.util.Date;

public class SmokeDetectorMessage extends AbstractDeviceMessage {
    private Date lastTested; //date of last test
    private boolean testStatus; //true if need to test, false if dont need to test
    private boolean alarmStatus; //true if alarm is ready, false if alarm is not ready

    public SmokeDetectorMessage(int id, String name, Date lastTested, boolean testStatus, boolean alarmStatus) {
        super(name, id);
        this.lastTested = lastTested;
        this.testStatus = testStatus;
        this.alarmStatus = alarmStatus;
    }

    public Date getLastTested() {
        return lastTested;
    }

    public boolean getTestStatus() {
        return testStatus;
    }

    public boolean getAlarmStatus() {
        return alarmStatus;
    }
}
