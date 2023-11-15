package messages;

import java.util.Date;

public class SmokeDetectorMessage extends AbstractDeviceMessage{
    private Date lastTested; //date of last test
    private boolean testStatus; //true if need to test, false if dont need to test
    private boolean alarmStatus; //true if alarm is ready, false if alarm is not ready
    private boolean alarm; //true if alarm is going off, false if alarm is not going off

    public SmokeDetectorMessage(boolean type, int deviceID, String name, boolean connectionStatus, int battery, boolean status, Date lastTested, boolean testStatus, boolean alarmStatus, boolean alarm){
        super(type, deviceID, name, connectionStatus, battery, status);
        this.lastTested = lastTested;
        this.testStatus = testStatus;
        this.alarmStatus = alarmStatus;
        this.alarm = alarm;
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

    public boolean getAlarm() {
        return alarm;
    }
}
