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
