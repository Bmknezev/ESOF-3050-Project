//-----------------------------------------------------------------
// PinMessage.java
// Group 2
// Description: Represents a message regarding PIN status and changes.
// Created By: Braydon
// Edited By:
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - pin: int - Current PIN associated with a device.
//   - newPin: int - New PIN requested for a device (if applicable).
//   - deviceID: int - ID of the device related to the PIN.
//   - pinStatus: boolean - Status indicating the success or failure of PIN-related operations.
//
//-----------------------------------------------------------------

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
