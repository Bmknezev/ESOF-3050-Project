package smartDevice;

public class SmartLock extends SmartDevice{
    private boolean lockStatus; //true = locked, false = unlocked
    private int password; //password to unlock the door
    private int timer; //timer to lock the door after a certain amount of time

    public SmartLock(boolean connectionStatus, int battery, boolean status, boolean lockStatus){
        super(connectionStatus, battery, status);
        this.lockStatus = lockStatus;
    }

    public void setLockStatus(boolean lockStatus){
        this.lockStatus = lockStatus;
    }

    public void setPassword(int password){
        this.password = password;
    }

    public boolean getLockStatus(){
        return lockStatus;
    }

    public int getPassword(){
        return password;
    }

    public Boolean authenticatePassword(int password){
        if (this.password == password){
            return true;
        }
        else{
            this.alertOwner();
            return false;
        }
    }

    public void setTimer(int timer){
        this.timer = timer;
    }

    public int getTimer(){
        return timer;
    }

    public void alertOwner(){
        System.out.println("ALERT: Someone is trying to break into your house!");
    }

}
