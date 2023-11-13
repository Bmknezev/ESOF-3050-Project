package ClientServer;


import GUI.Control.AbstractController;
import GUI.Control.DeviceSelectionMenuController;

import java.io.IOException;

public class SmartHomeClient extends ClientServer.AbstractClient {
private AbstractController tmp;
private DeviceSelectionMenuController tmp2;
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
        String[] s1 = msg.toString().split("@");
        if(s1[0].equals("1")){
            String[] s = s1[1].split("~");
            for (String string : s) {
                tmp2.addNewDevice(string);
            }
        }else {
            String[] s = s1[1].split("\\|");
            tmp.update(s);
        }
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

    public void getDevices(DeviceSelectionMenuController deviceSelectionPaneController) {
        tmp2 = deviceSelectionPaneController;
        String s = true + "@" + -1;
        try {
            sendToServer(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
