package messages.server;

import messages.AbstractDeviceMessage;

public class GarageDoorMessage extends AbstractDeviceMessage {
    private boolean safteySwitch; //true if object is detected under door, false if no object is detected
    private boolean doorStatus; //true if door is open, false if door is closed
    private boolean moving; //true if door is moving, false if door is not moving
    private boolean usePassword; //true if password is needed to open door, false if password is not needed
    private int password; //password to open door
    private int passwordAttempts; //number of times password has been entered incorrectly

    public GarageDoorMessage(int id, String name, boolean safteySwitch, boolean doorStatus, boolean moving, boolean usePassword, int password, int passwordAttempts){
        super(name, id);
        this.safteySwitch = safteySwitch;
        this.doorStatus = doorStatus;
        this.moving = moving;
        this.usePassword = usePassword;
        this.password = password;
        this.passwordAttempts = passwordAttempts;
    }

    public GarageDoorMessage(int id, String name, boolean doorStatus, boolean usePassword, int password){
        super(name, id);
        this.usePassword = usePassword;
        this.password = password;
        this.passwordAttempts = 0;
        this.doorStatus = doorStatus;
    }

    public boolean getSafteySwitch() {
        return safteySwitch;
    }

    public boolean getDoorStatus() {
        return doorStatus;
    }

    public boolean getMoving() {
        return moving;
    }

    public boolean getUsePassword() {
        return usePassword;
    }

    public int getPassword() {
        return password;
    }

    public int getPasswordAttempts() {
        return passwordAttempts;
    }

    public int getPIN() {
        return password;
    }
}
