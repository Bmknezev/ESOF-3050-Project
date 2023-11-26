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
