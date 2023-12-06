//-----------------------------------------------------------------
// ThermostatAutomationMessage.java
// Group 2
// Description: Represents an automation message for a thermostat in a system.
//              Extends AbstractAutomationMessage.
// Created By: Braydon
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - temperature: int - Represents the temperature for the thermostat.
//   - thermostatStatus: boolean - Represents the status of the thermostat.
//-----------------------------------------------------------------

package messages.automations;

import java.time.LocalDate;
import java.util.Date;

public class ThermostatAutomationMessage extends AbstractAutomationMessage{
    private float temperature;

    public ThermostatAutomationMessage(int deviceID, float temperature, Date date) {
        super(deviceID, date, 2);
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }
}
