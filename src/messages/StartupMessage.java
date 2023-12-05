//-----------------------------------------------------------------
// StartupMessage.java
// Group 2
// Description: Represents a message for system or client startup.
// Created By:
// Edited By:
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - clientID: int - ID associated with the client initiating startup.
//
//-----------------------------------------------------------------

package messages;

public class StartupMessage extends AbstractMessage{
    private int clientID;

    public StartupMessage(int clientID){
        //used by both server and client
        super(3);
        this.clientID = clientID;
    }

    public int getClientID(){
        return clientID;
    }

}
