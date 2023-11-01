import java.io.Serializable;

public class light implements Serializable {
    int power;
    float brightness;

    public light(int power, float brightness){
        this.power = power;
        this.brightness = brightness;
    }

    public void setPower(int power){
        this.power = power;
    }

    public void setBrightness(float brightness){
        this.brightness = brightness;
    }

    public int getPower(){
        return power;
    }

    public float getBrightness(){
        return brightness;
    }

    public String toString(){
        return "Power: " + power + " Brightness: " + brightness;
    }


}
