import GUI.Control.AbstractController;


import java.io.IOException;

public class SmartHomeClient extends AbstractClient{
private AbstractController tmp;
    /**
     * Constructs the client.
     *
     * @param host the server's host name.
     * @param port the port number.
     */
    public SmartHomeClient(String host, int port) {
        super(host, port);
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        System.out.println("Message received");
        tmp.link(msg);
    }

    public void request(int i, AbstractController c) {
        tmp = c;
        try {
            sendToServer(i);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
