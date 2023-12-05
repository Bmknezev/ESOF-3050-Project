package messages.automations;

import messages.AbstractMessage;

import java.time.LocalDate;
import java.util.Date;

public class AbstractAutomationMessage extends AbstractMessage {

    private int deviceID, deviceType;
    private LocalDate date;

    public AbstractAutomationMessage(int deviceID, LocalDate date, int deviceType) {
        super(4);
        this.deviceID = deviceID;
        this.date = date;
        this.deviceType = deviceType;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getDeviceType() {
        return deviceType;
    }

}
