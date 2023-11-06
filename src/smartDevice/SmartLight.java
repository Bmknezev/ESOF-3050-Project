package smartDevice;

public class SmartLight extends SmartDevice{
    private int colour; //hexadecimal colour value (e.g. 0x000000 is black, 0xFFFFFF is white)
    private int brightness; //brightness value from 0 to 100
    private boolean lightStatus; //true if light is on, false if light is off
    private String name;

    public SmartLight(String name, boolean connectionStatus, int battery, boolean status, int colour, int brightness, boolean lightStatus){
        super(connectionStatus, battery, status);
        this.colour = colour;
        this.brightness = brightness;
        this.lightStatus = lightStatus;
        this.name = name;
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

    public int getBrightness(){
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
}
