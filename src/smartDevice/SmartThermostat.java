package smartDevice;
public class SmartThermostat extends SmartDevice{
    private float temperature; //the current temperature
    private float setpoint; //the temperature the thermostat is set to
    private boolean heatEnabled; //if true, heat is enabled
    private boolean coolEnabled; //if true, cool is enabled

    public SmartThermostat(boolean connectionStatus, int battery, boolean status, String name, float temperature, float setpoint, boolean heatEnabled, boolean coolEnabled){
        super(connectionStatus, battery, status, name);
        this.temperature = temperature;
        this.setpoint = setpoint;
        this.heatEnabled = heatEnabled;
        this.coolEnabled = coolEnabled;
    }

    public void setTemperature(float temperature){
        this.temperature = temperature;
    }

    public void setSetpoint(float setpoint){
        this.setpoint = setpoint;
    }

    public void setHeatEnabled(boolean heatEnabled){
        this.heatEnabled = heatEnabled;
    }

    public void setCoolEnabled(boolean coolEnabled){
        this.coolEnabled = coolEnabled;
    }

    @Override
    public String getDeviceType() { return "Smart Thermostat"; }

    public float getTemperature(){
        return temperature;
    }

    public float getSetpoint(){
        return setpoint;
    }

    public boolean getHeatEnabled(){
        return heatEnabled;
    }

    public boolean getCoolEnabled(){
        return coolEnabled;
    }
}
