//-----------------------------------------------------------------
// CoffeeAutomationMessage.java
// Group 2
// Description: Represents a message for coffee machine automation in a system.
//              Extends AbstractAutomationMessage.
// Created By: Braydon
// Edited By: Braydon, Liam
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - coffeeMachineID: int - Identifier for the coffee machine
//   - coffeeMachineAction: int - Action code for the coffee machine (e.g., start brewing, stop brewing, etc.)
//-----------------------------------------------------------------

package messages.automations;

import java.util.Date;

public class CoffeeAutomationMessage extends AbstractAutomationMessage{

    public CoffeeAutomationMessage(int deviceID, Date date) {
        super(deviceID, date, 3);
    }
}
