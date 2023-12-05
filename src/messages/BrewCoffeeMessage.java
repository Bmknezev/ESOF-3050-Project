//-----------------------------------------------------------------
// BrewCoffeeMessage.java
// Group 2
// Description: Represents a message for brewing coffee.
// Created By: Braydon
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - size: String - Represents the size of the brewed coffee.
//   - strength: String - Indicates the strength of the brewed coffee.
//   - waterLevel: double - Represents the water level for brewing.
//   - coffeeBeanLevel: double - Indicates the coffee bean level for brewing.
//   - coffeeLevel: double - Represents the coffee level for brewing.
//   - temperature: String - Indicates the temperature for brewing.
//
//-----------------------------------------------------------------

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
