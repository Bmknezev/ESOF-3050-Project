package messages;

public class CoffeeMessage extends AbstractDeviceMessage{
    private boolean cupStatus; //true if cup is in place, false if cup is not in place
    private double waterLevel; //0-1
    private double coffeeBeanLevel; //0-1
    private int timer; //0-60
    private String coffeeType; //lets user input type of coffee
    private boolean readyToBrew; //true if ready to brew, false if not ready to brew
    private boolean brewing; //true if brewing, false if not brewing
    private double coffeeLevel; //amount of coffee in pot

    public CoffeeMessage(boolean type, int id, String name, boolean connectionStatus, int battery, boolean status, boolean cupStatus, double waterLevel, double coffeeBeanLevel, int timer, String coffeeType, boolean readyToBrew, boolean brewing, double coffeeLevel){
        super(type, id, name, connectionStatus, battery, status);
        this.cupStatus = cupStatus;
        this.waterLevel = waterLevel;
        this.coffeeBeanLevel = coffeeBeanLevel;
        this.timer = timer;
        this.coffeeType = coffeeType;
        this.readyToBrew = readyToBrew;
        this.brewing = brewing;
        this.coffeeLevel = coffeeLevel;
    }

    public boolean getCupStatus(){
        return cupStatus;
    }

    public double getWaterLevel(){
        return waterLevel;
    }

    public double getCoffeeBeanLevel(){
        return coffeeBeanLevel;
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

    public double getCoffeeLevel(){
        return coffeeLevel;
    }
}
