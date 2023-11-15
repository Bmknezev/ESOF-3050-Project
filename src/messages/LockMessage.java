package messages;

public class LockMessage extends AbstractDeviceMessage{

        private boolean lockStatus;
        private int password;
        private int timer;


        public LockMessage(boolean type, int id, String name, boolean connectionStatus, int battery, boolean status, boolean lockStatus, int password, int timer){
            super(type, id, name, connectionStatus, battery, status);
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
