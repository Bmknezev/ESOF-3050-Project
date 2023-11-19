package messages;

public class PinMessage extends AbstractMessage{
    private String pin;
    private boolean pinStatus;

    public PinMessage(int type, String pin) {
        super(6);
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    public void setPinStatus(boolean pinStatus) {
        this.pinStatus = pinStatus;
    }

    public boolean getPinStatus() {
        return pinStatus;
    }
}
