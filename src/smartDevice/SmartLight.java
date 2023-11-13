package smartDevice;

public class SmartLight extends SmartDevice{
    private int colour; //hexadecimal colour value (e.g. 0x000000 is black, 0xFFFFFF is white)
    private int brightness; //brightness value from 0 to 100
    private boolean lightStatus; //true if light is on, false if light is off

    private int deviceID;

    public SmartLight(boolean connectionStatus, int battery, boolean status, String name, int colour, int brightness, boolean lightStatus, int id){
        super(connectionStatus, battery, status, name);
        this.colour = colour;
        this.brightness = brightness;
        this.lightStatus = lightStatus;
        this.deviceID = id;
    }

    public void setColour(int colour){
        this.colour = Integer.parseInt(String.valueOf(colour), 16);
    }

    public void setBrightness(int brightness){
        this.brightness = brightness;
    }

    public void setLightStatus(boolean lightStatus){
        this.lightStatus = lightStatus;
    }

    public void setDeviceID(int id){
        this.deviceID = id;
    }

    @Override
    public String getDeviceType() { return "Smart Light"; }

    public int getColour(){
        return colour;
    }

    public float getBrightness(){
        return brightness;
    }

    public boolean getLightStatus(){
        return lightStatus;
    }

    public int getDeviceID(){
        return deviceID;
    }
}
