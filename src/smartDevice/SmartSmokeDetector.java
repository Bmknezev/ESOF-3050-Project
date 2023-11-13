package smartDevice;

import java.util.Date;

public class SmartSmokeDetector extends SmartDevice{
    private Date lastTested; //date of last test
    private boolean testStatus; //true if need to test, false if dont need to test
    private boolean alarmStatus; //true if alarm is ready, false if alarm is not ready
    private boolean alarm; //true if alarm is going off, false if alarm is not going off

    public SmartSmokeDetector(boolean connectionStatus, int battery, boolean status, String name){
        super(connectionStatus, battery, status, name);
        this.lastTested = new Date();
        this.testStatus = true;
        this.alarmStatus = false;
        this.alarm = false;
    }

    public void setLastTested(Date lastTested){
        this.lastTested = lastTested;
    }

    public void setTestStatus(boolean testStatus){
        this.testStatus = testStatus;
    }

    public void setAlarmStatus(boolean alarmStatus){
        this.alarmStatus = alarmStatus;
    }

    public void setAlarm(boolean alarm){
        this.alarm = alarm;
    }

    @Override
    public String getDeviceType() { return "Smart Smoke Detector"; }

    public Date getLastTested(){
        return lastTested;
    }

    public boolean getTestStatus(){
        return testStatus;
    }

    public boolean getAlarmStatus(){
        return alarmStatus;
    }

    public boolean getAlarm(){
        return alarm;
    }
}
