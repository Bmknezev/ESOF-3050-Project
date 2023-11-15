package messages.server;

import messages.AbstractDeviceMessage;

public class LockMessage extends AbstractDeviceMessage {

        private boolean lockStatus;
        private int password;
        private int timer;


        public LockMessage(int id, String name, boolean lockStatus, int password, int timer){
            super(name, id);
            this.lockStatus = lockStatus;
            this.password = password;
            this.timer = timer;
        }

        public boolean getLockStatus() {
            return lockStatus;
        }

        public int getPassword() {
            return password;
        }

        public int getTimer() {
            return timer;
        }
}
