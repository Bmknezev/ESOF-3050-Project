import java.io.IOException;
import java.util.Objects;

public class SmartHomeClient extends AbstractClient{

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
        String message = msg.toString();
        System.out.println(message);
    }
}
