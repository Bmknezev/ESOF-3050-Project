package messages;

public class LightMessage extends AbstractDeviceMessage{

    private int colour;
    private int brightness;
    private boolean lightStatus;

    public LightMessage(boolean type, int id, String name, boolean connectionStatus, int battery, boolean status, int colour, int brightness, boolean lightStatus){
        super(type, id, name, connectionStatus, battery, status);
        this.colour = colour;
        this.brightness = brightness;
        this.lightStatus = lightStatus;
    }

    public int getColour() {
        return colour;
    }

    public int getBrightness() {
        return brightness;
    }

    public boolean getLightStatus() {
        return lightStatus;
    }
}
