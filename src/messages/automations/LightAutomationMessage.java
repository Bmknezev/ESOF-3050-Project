package messages.automations;

import java.time.LocalDate;
import java.util.Date;

public class LightAutomationMessage extends AbstractAutomationMessage{
    private String colour;
    private int brightness;
    private boolean lightStatus;

    public LightAutomationMessage(int deviceID, String colour, int brightness, boolean lightStatus, LocalDate date) {
        super(deviceID, date, 0);
        this.colour = colour;
        this.brightness = brightness;
        this.lightStatus = lightStatus;
    }

    public String getColour() {
        return colour;
    }

    public int getBrightness() {
        return brightness;
    }

    public boolean getLightStatus() {
        return lightStatus;
    }
}
