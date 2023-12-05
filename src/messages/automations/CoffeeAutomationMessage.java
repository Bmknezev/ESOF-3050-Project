//-----------------------------------------------------------------
// CoffeeAutomationMessage.java
// Group 2
// Description: Represents a message for coffee machine automation in a system.
//              Extends AbstractAutomationMessage.
// Created By: Braydon
// Edited By: Francisco, Braydon
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - coffeeMachineID: int - Identifier for the coffee machine
//   - coffeeMachineAction: int - Action code for the coffee machine (e.g., start brewing, stop brewing, etc.)
//-----------------------------------------------------------------

package messages.automations;

import java.time.LocalDate;
import java.util.Date;

public class CoffeeAutomationMessage extends AbstractAutomationMessage{
    private int coffeeMachineID;
    private int coffeeMachineAction;

    public CoffeeAutomationMessage(int deviceID, int coffeeMachineID, int coffeeMachineAction, Date date) {
        super(deviceID, date, 3);
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
