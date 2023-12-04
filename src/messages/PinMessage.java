package messages;

public class PinMessage extends AbstractMessage{
    private int pin;
    private int newPin;
    private int deviceID;
    private boolean pinStatus;


    public PinMessage(int id, int pin, int newPin, boolean pinStatus) {
        super(6);
        this.pin = pin;
        this.deviceID = id;
        this.newPin = newPin;
        this.pinStatus = pinStatus;
    }

    public int getPin() {
        return pin;
    }

    public void setPinStatus(boolean pinStatus) {
        this.pinStatus = pinStatus;
    }

    public boolean getPinStatus() {
        return pinStatus;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public int getNewPin() {
        return newPin;
    }
}
