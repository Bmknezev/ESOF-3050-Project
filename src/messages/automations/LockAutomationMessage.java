package messages.automations;

import java.util.Date;

public class LockAutomationMessage extends AbstractAutomationMessage{
    private boolean lockStatus;
    private int timer;
    private int pin;

    public LockAutomationMessage(int deviceID, boolean lockStatus, int timer, int pin, Date date) {
        super(deviceID, date);
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
