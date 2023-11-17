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
