package messages;

public class StartupMessage extends AbstractMessage{
    private int clientID;

    public StartupMessage(boolean type, int clientID){
        super(type, true);
        this.clientID = clientID;
    }

    public int getClientID(){
        return clientID;
    }

}
