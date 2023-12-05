package messages.automations;

import java.util.Date;

public class ThermostatAutomationMessage extends AbstractAutomationMessage{
    private int temperature;
    private boolean thermostatStatus;

    public ThermostatAutomationMessage(int deviceID, int temperature, boolean thermostatStatus, Date date) {
        super(deviceID, date, 2);
        this.temperature = temperature;
        this.thermostatStatus = thermostatStatus;
    }

    public int getTemperature() {
        return temperature;
    }

    public boolean getThermostatStatus() {
        return thermostatStatus;
    }
}
