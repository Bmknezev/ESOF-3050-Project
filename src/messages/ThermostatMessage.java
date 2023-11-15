package messages;

public class ThermostatMessage extends AbstractDeviceMessage{
    private float temperature; //the current temperature
    private float setpoint; //the temperature the thermostat is set to
    private boolean heatEnabled; //if true, heat is enabled
    private boolean coolEnabled; //if true, cool is enabled
    private boolean mode;

    public ThermostatMessage(boolean type, int id, String name, boolean connectionStatus, int battery, boolean status, float temperature, float setpoint, boolean heatEnabled, boolean coolEnabled, boolean mode){
        super(type, id, name, connectionStatus, battery, status);
        this.temperature = temperature;
        this.setpoint = setpoint;
        this.heatEnabled = heatEnabled;
        this.coolEnabled = coolEnabled;
        this.mode = mode;
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

    public boolean getMode() {
        return mode;
    }
}
