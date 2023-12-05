//-----------------------------------------------------------------
// AbstractMessage.java
// Group 2
// Description: Base abstract class for messages.
// Created By: Braydon
// Edited By:
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - type: int - Indicates the type of message. 1 for requesting device details, 2 for receiving device details, 3 for new client message, 4 for automation message.
//
//-----------------------------------------------------------------

package messages;

public abstract class AbstractMessage implements java.io.Serializable{
   private int type; //1 for requesting device details, 2 for receiving device details, 3 for new client message, 4 for automation message


    public AbstractMessage(int type) {
        //constructor
        this.type = type;
    }

    public int getType(){
        return type;
    }
}
