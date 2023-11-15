package messages;

public abstract class AbstractMessage implements java.io.Serializable{
    private boolean request; //true for request, false for response
    private boolean startup = false; //true for startup, false for normal operation


    public AbstractMessage(boolean type) {
        this.request = type;
    }

    public AbstractMessage(boolean type, boolean startup) {
        this.request = type;
        this.startup = startup;
    }

    public boolean getMessageType() {
        return request;
    }

    public boolean getStartup(){
        return startup;
    }

    public void setStartup(boolean startup){
        this.startup = startup;
    }
}
