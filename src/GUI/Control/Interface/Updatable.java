package GUI.Control.Interface;

import messages.AbstractDeviceMessage;

public interface Updatable {

    public abstract void update(AbstractDeviceMessage msg);
}
