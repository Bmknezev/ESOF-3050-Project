package smartDevice;

import java.io.Serializable;

public abstract class SmartDevice implements Serializable {
    private boolean connectionStatus; //device connected to network
    private int battery; //battery level

    private boolean status; //device ready to use


    public SmartDevice(boolean connectionStatus, int battery, boolean status){
        this.connectionStatus = connectionStatus;
        this.battery = battery;
        this.status = status;
    }

    public void setConnectionStatus(boolean connectionStatus){
        this.connectionStatus = connectionStatus;
    }

    public void setBattery(int battery){
        this.battery = battery;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean getConnectionStatus(){
        return connectionStatus;
    }

    public int getBattery(){
        return battery;
    }

    public boolean getStatus(){
        return status;
    }


}
