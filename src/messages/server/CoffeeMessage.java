package messages.server;

import messages.AbstractDeviceMessage;

public class CoffeeMessage extends AbstractDeviceMessage {
    private boolean cupStatus; //true if cup is in place, false if cup is not in place
    private double waterLevel; //0-1
    private double coffeeBeanLevel; //0-1
    private String coffeeType; //lets user input type of coffee
    private boolean readyToBrew; //true if ready to brew, false if not ready to brew
    private double coffeeLevel; //amount of coffee in pot

    public CoffeeMessage( int id, String name, boolean cupStatus, double waterLevel, double coffeeBeanLevel, String coffeeType, boolean readyToBrew, double coffeeLevel){
        super(name, id);
        this.cupStatus = cupStatus;
        this.waterLevel = waterLevel;
        this.coffeeBeanLevel = coffeeBeanLevel;
        this.coffeeType = coffeeType;
        this.readyToBrew = readyToBrew;
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

    public String getCoffeeType(){
        return coffeeType;
    }

    public boolean getReadyToBrew(){
        return readyToBrew;
    }

    public double getCoffeeLevel(){
        return coffeeLevel;
    }
}
