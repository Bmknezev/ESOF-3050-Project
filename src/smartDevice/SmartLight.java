package smartDevice;

public class SmartLight extends SmartDevice{
    private int colour; //hexadecimal colour value (e.g. 0x000000 is black, 0xFFFFFF is white)
    private int brightness; //brightness value from 0 to 100
    private boolean lightStatus; //true if light is on, false if light is off

    private String name;
    private int deviceID;

    public SmartLight(String name, int id, boolean connectionStatus, int battery, boolean status, int colour, int brightness, boolean lightStatus){
        super(connectionStatus, battery, status);
        this.colour = colour;
        this.brightness = brightness;
        this.lightStatus = lightStatus;
        this.name = name;
        this.deviceID = id;
    }

    public void setColour(int colour){
        this.colour = Integer.parseInt(String.valueOf(colour), 16);
    }

    public void setBrightness(int brightness){
        this.brightness = brightness;
    }

    public int getColour(){
        return colour;
    }

    public float getBrightness(){
        return brightness;
    }

    public void setLightStatus(boolean lightStatus){
        this.lightStatus = lightStatus;
    }

    public boolean getLightStatus(){
        return lightStatus;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getDeviceID(){
        return deviceID;
    }

    public void setDeviceID(int id){
        this.deviceID = id;
    }


}
