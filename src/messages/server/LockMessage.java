//-----------------------------------------------------------------
// LockMessage.java
// Group 2
// Description: Represents a message related to controlling and querying the status of a lock.
// Created By: Braydon
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - lockStatus: boolean - Represents the current status of the lock (locked/unlocked).
//   - timer: int - Indicates the timer for the lock (if any).
//   - pin: int - Represents the PIN associated with the lock (if applicable).
// Error Handling: Not specified within this class. Custom error handling can be implemented.
//-----------------------------------------------------------------

package messages.server;

import messages.AbstractDeviceMessage;

public class LockMessage extends AbstractDeviceMessage {

        private boolean lockStatus;
        private int timer;
        private int pin;


        public LockMessage(int id, String name, boolean lockStatus, int timer, int pin){
            super(name, id);
            this.lockStatus = lockStatus;
            this.timer = timer;
            this.pin = pin;
        }

        public boolean getLockStatus() {
            return lockStatus;
        }

        public int getTimer() {
            return timer;
        }

    public int getPIN() {
        return pin;
    }
}
