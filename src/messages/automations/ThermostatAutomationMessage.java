//-----------------------------------------------------------------
// ThermostatAutomationMessage.java
// Group 2
// Description: Represents an automation message for a thermostat in a system.
//              Extends AbstractAutomationMessage.
// Created By: Braydon
// Edited By:
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - temperature: int - Represents the temperature for the thermostat.
//   - thermostatStatus: boolean - Represents the status of the thermostat.
//-----------------------------------------------------------------

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
