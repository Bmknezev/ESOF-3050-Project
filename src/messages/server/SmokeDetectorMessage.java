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
