//-----------------------------------------------------------------
// ThermostatMessage.java
// Group 2
// Description: Represents a message related to thermostats.
// Created By: Braydon
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - temperature: float - Represents the current temperature.
//   - setpoint: float - Represents the temperature the thermostat is set to.
//   - heatEnabled: boolean - Indicates if heat is enabled (true) or not (false).
//   - coolEnabled: boolean - Indicates if cool is enabled (true) or not (false).
//   - mode: int - Represents the mode of the thermostat (0 for off, 1 for heat, 2 for cool).
//
//-----------------------------------------------------------------

package messages.server;

import messages.AbstractDeviceMessage;

public class ThermostatMessage extends AbstractDeviceMessage {
    private float temperature; //the current temperature
    private float setpoint; //the temperature the thermostat is set to
    private boolean heatEnabled; //if true, heat is enabled
    private boolean coolEnabled; //if true, cool is enabled
    private int mode; //0 for off, 1 for heat, 2 for cool

    public ThermostatMessage(int id, String name, float temperature, float setpoint, boolean heatEnabled, boolean coolEnabled, int mode){
        super(name, id);
        this.temperature = temperature;
        this.setpoint = setpoint;
        this.heatEnabled = heatEnabled;
        this.coolEnabled = coolEnabled;
        this.mode = mode;
    }

    public ThermostatMessage(int id, String name, float setpoint, boolean heatEnabled, boolean coolEnabled){
        super(name, id);
        this.setpoint = setpoint;
        this.heatEnabled = heatEnabled;
        this.coolEnabled = coolEnabled;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getSetpoint() {
        return setpoint;
    }

    public boolean getHeatEnabled() {
        return heatEnabled;
    }

    public boolean getCoolEnabled() {
        return coolEnabled;
    }

    public int getMode() {
        return mode;
    }
}
