package ClientServer;


import GUI.Control.Abstract.AbstractDeviceController;
import GUI.Control.DeviceSelectionMenuController;
import messages.AbstractDeviceMessage;
import messages.AbstractMessage;
import messages.NewDeviceMessage;
import messages.StartupMessage;

import java.io.IOException;

public class SmartHomeClient extends ClientServer.AbstractClient {
private AbstractDeviceController tmp;
private DeviceSelectionMenuController tmp2;
private int clientID = -1;
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

           //check message type
        switch (((AbstractMessage)msg).getType()){
            case 1:
                System.out.println("Device details received.");
                //device details received
                tmp.update((AbstractDeviceMessage)msg);
                break;
            case 2:
                //new device received
                tmp2.addNewDevice((NewDeviceMessage)msg);
                break;
            case 3:
                //client id received
                clientID = ((StartupMessage)msg).getClientID();
                break;
            default:
                System.out.println("Unknown message type received.");
                break;
        }
    }

    public void request(int i, AbstractDeviceController c) {
        //request a device from server with device id i
        tmp = c;
        NewDeviceMessage msg = new NewDeviceMessage(i);
        Send(msg);
    }

    public void getDevices(DeviceSelectionMenuController deviceSelectionPaneController) {
        tmp2 = deviceSelectionPaneController;
        StartupMessage msg = new StartupMessage( -1);
        Send(msg);
    }

    public void UpdateServer(AbstractDeviceMessage msg){
        Send(msg);
    }

    private void Send(Object msg){
        try {
            sendToServer(msg);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
