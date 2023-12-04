package messages.automations;

import java.util.Date;

public class CoffeeAutomationMessage extends AbstractAutomationMessage{
    private int coffeeMachineID;
    private int coffeeMachineAction;

    public CoffeeAutomationMessage(int deviceID, int coffeeMachineID, int coffeeMachineAction, Date date) {
        super(deviceID, date);
        this.coffeeMachineID = coffeeMachineID;
        this.coffeeMachineAction = coffeeMachineAction;
    }

    public int getCoffeeMachineID() {
        return coffeeMachineID;
    }

    public int getCoffeeMachineAction() {
        return coffeeMachineAction;
    }
}
