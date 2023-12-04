package messages.automations;

import java.util.Date;

public class GarageDoorAutomationMessage extends AbstractAutomationMessage{
    private boolean doorStatus;

    public GarageDoorAutomationMessage(int deviceID, boolean doorStatus, Date date) {
        super(deviceID, date);
        this.doorStatus = doorStatus;
    }

    public boolean getDoorStatus() {
        return doorStatus;
    }
}
