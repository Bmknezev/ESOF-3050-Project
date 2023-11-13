package smartDevice;

public class SmartGarageDoor extends SmartDevice{
    private boolean safteySwitch; //true if object is detected under door, false if no object is detected
    private boolean doorStatus; //true if door is open, false if door is closed
    private boolean moving; //true if door is moving, false if door is not moving
    private int password; //password to open door
    private boolean usePassword; //true if password is needed to open door, false if password is not needed
    private int passwordAttempts; //number of times password has been entered incorrectly

    public SmartGarageDoor(boolean connectionStatus, int battery, boolean status, String name, boolean safteySwitch, boolean doorStatus, boolean moving){
        super(connectionStatus, battery, status, name);
        this.safteySwitch = safteySwitch;
        this.doorStatus = doorStatus;
        this.moving = moving;
        this.passwordAttempts = 0;
    }

    public Boolean authenticatePassword(int password){
        if (this.password == password){
            passwordAttempts = 0;
            return true;
        }
        else{
            passwordAttempts++;
            if(passwordAttempts >= 3){
                this.alertOwner();
            }
            return false;
        }
    }

    public void alertOwner(){
        System.out.println("ALERT: Someone is trying to break into your house!");
    }

    public void setSafteySwitch(boolean safteySwitch){
        this.safteySwitch = safteySwitch;
    }

    public void setDoorStatus(boolean doorStatus){
        this.doorStatus = doorStatus;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    public void setPassword(int password){
        this.password = password;
    }

    public void setUsePassword(boolean usePassword, int password){
        this.usePassword = usePassword;
        this.setPassword(password);
    }

    @Override
    public String getDeviceType() { return "Smart Garage Door Opener"; }

    public boolean getSafteySwitch(){
        return safteySwitch;
    }

    public boolean getDoorStatus(){
        return doorStatus;
    }

    public boolean getMoving() { return moving; }

    public int getPassword() { return password; }

    public boolean getUsePassword(){
        return usePassword;
    }
}
