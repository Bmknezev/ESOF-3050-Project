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
