package messages;

public abstract class AbstractMessage implements java.io.Serializable{
   private int type; //1 for requesting device details, 2 for receiving device details, 3 for new client message


    public AbstractMessage(int type) {
        //constructor
        this.type = type;
    }

    public int getType(){
        return type;
    }
}
