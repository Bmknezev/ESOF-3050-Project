//-----------------------------------------------------------------
// LightMessage.java
// Group 2
// Description: Represents a message related to controlling and querying the status of a light.
// Created By: Braydon
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - colour: String - Represents the color of the light.
//   - brightness: int - Represents the brightness level of the light.
//   - lightStatus: boolean - Indicates the status of the light (on/off).
//
//-----------------------------------------------------------------

package messages.server;

import messages.AbstractDeviceMessage;

public class LightMessage extends AbstractDeviceMessage {

    private String colour;
    private int brightness;
    private boolean lightStatus;

    public LightMessage(int id, String name,String colour, int brightness, boolean lightStatus){
        super(name, id);
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
