//-----------------------------------------------------------------
// LightAutomationMessage.java
// Group 2
// Description: Represents a message for light automation in a system.
//              Extends AbstractAutomationMessage.
// Created By:
// Edited By:
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - colour: String - Represents the color of the light.
//   - brightness: int - Represents the brightness level of the light.
//   - lightStatus: boolean - Represents the status of the light (on or off).
//-----------------------------------------------------------------

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
