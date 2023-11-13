package smartDevice;

public class SmartCoffeeMachine extends SmartDevice{
    private boolean cupStatus; //true if cup is in place, false if cup is not in place
    private int waterLevel; //0-100
    private int coffeeLevel; //0-100
    private int timer; //0-60
    private String coffeeType; //lets user input type of coffee
    private boolean readyToBrew; //true if ready to brew, false if not ready to brew
    private boolean brewing; //true if brewing, false if not brewing

    public SmartCoffeeMachine(boolean connectionStatus, int battery, boolean status, String name){
        super(connectionStatus, battery, status, name);
        this.cupStatus = false;
        this.waterLevel = 0;
        this.coffeeLevel = 0;
        this.timer = 0;
        this.coffeeType = "none";
        this.readyToBrew = false;
        this.brewing = false;
    }

    public void setCupStatus(boolean cupStatus){
        this.cupStatus = cupStatus;
    }

    public void setWaterLevel(int waterLevel){
        this.waterLevel = waterLevel;
    }

    public void setCoffeeLevel(int coffeeLevel){
        this.coffeeLevel = coffeeLevel;
    }

    public void setTimer(int timer){
        this.timer = timer;
    }

    public void setCoffeeType(String coffeeType){
        this.coffeeType = coffeeType;
    }

    public void setReadyToBrew(boolean readyToBrew){
        this.readyToBrew = readyToBrew;
    }

    public void setBrewing(boolean brewing){
        this.brewing = brewing;
    }

    @Override
    public String getDeviceType() { return "Smart Coffee Machine"; }

    public boolean getCupStatus(){
        return cupStatus;
    }

    public int getWaterLevel(){
        return waterLevel;
    }

    public int getCoffeeLevel(){
        return coffeeLevel;
    }

    public int getTimer(){
        return timer;
    }

    public String getCoffeeType(){
        return coffeeType;
    }

    public boolean getReadyToBrew(){
        return readyToBrew;
    }

    public boolean getBrewing(){
        return brewing;
    }
}
