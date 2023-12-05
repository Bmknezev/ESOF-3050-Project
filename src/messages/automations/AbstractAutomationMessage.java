package messages.automations;

import messages.AbstractMessage;

import java.util.Date;

public class AbstractAutomationMessage extends AbstractMessage {

    private int deviceID, deviceType;
    private Date date;

    public AbstractAutomationMessage(int deviceID, Date date, int deviceType) {
        super(4);
        this.deviceID = deviceID;
        this.date = date;
        this.deviceType = deviceType;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public Date getDate() {
        return date;
    }

    public int getDeviceType() {
        return deviceType;
    }

}
