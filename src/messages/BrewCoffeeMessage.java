package messages;

public class BrewCoffeeMessage extends AbstractDeviceMessage{

    private String size;
    private String strength;
    private double waterLevel;
    private double coffeeBeanLevel;
    private double coffeeLevel;
    private String temperature;

    public BrewCoffeeMessage(String name, int id, String size, String strength, double water, double beans, double coffee, String temp) {
        super(name, id);
        this.size = size;
        this.strength = strength;
        this.waterLevel = water;
        this.coffeeBeanLevel = beans;
        this.coffeeLevel = coffee;
        this.temperature = temp;
    }

    public String getSize() {
        return size;
    }

    public String getStrength() {
        return strength;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public double getCoffeeBeanLevel() {
        return coffeeBeanLevel;
    }

    public double getCoffeeLevel() {
        return coffeeLevel;
    }

    public String getTemperature() {
        return temperature;
    }
}
