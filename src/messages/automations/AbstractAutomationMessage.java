package messages.automations;

import messages.AbstractMessage;

import java.util.Date;

public class AbstractAutomationMessage extends AbstractMessage {

    private int deviceID;
    private Date date;

    public AbstractAutomationMessage(int deviceID, Date date) {
        super(4);
        this.deviceID = deviceID;
        this.date = date;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public Date getDate() {
        return date;
    }

}
