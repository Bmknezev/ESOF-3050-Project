package GUI.Control;


import java.io.IOException;

public class SmartHomeClient extends AbstractClient {
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
        System.out.println("Message received: " + msg.toString());
        String[] s = msg.toString().split("@");
        tmp.update(s);
    }

    public void request(int i, AbstractController c) {
        tmp = c;
        String s = true + "@" + i;
        try {
            sendToServer(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
