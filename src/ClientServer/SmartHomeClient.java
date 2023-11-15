package ClientServer;


import GUI.Control.Abstract.AbstractDeviceController;
import GUI.Control.DeviceSelectionMenuController;
import messages.AbstractDeviceMessage;
//import messages.DeviceRequestMessage;
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
        if (clientID == -1) {
            clientID = ((StartupMessage) msg).getClientID();
        }else if(((AbstractDeviceMessage)msg).getStartup()){
            tmp2.addNewDevice((NewDeviceMessage) msg);
        }
        else{
            ((AbstractDeviceController)tmp).update((AbstractDeviceMessage) msg);
        }
    }

    public void request(int i, AbstractDeviceController c) {
        //request a device from server with device id i
        tmp = c;
        //try {
            //DeviceRequestMessage msg = new DeviceRequestMessage(i, clientID);
            //sendToServer(msg);
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}
    }

    public void getDevices(DeviceSelectionMenuController deviceSelectionPaneController) {
        tmp2 = deviceSelectionPaneController;
        StartupMessage msg = new StartupMessage(true, -1);
        try {
            sendToServer(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateServer(AbstractDeviceMessage msg){
        String message = clientID + "?" + msg;
        System.out.println(message);
        try {
            sendToServer(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
