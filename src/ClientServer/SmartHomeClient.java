package ClientServer;


import GUI.Control.Abstract.AbstractDeviceController;
import GUI.Control.DeviceSelectionMenuController;
import messages.AbstractDeviceMessage;
import messages.AbstractMessage;
import messages.NewDeviceMessage;
import messages.StartupMessage;

public class SmartHomeClient extends ClientServer.AbstractClient {
private AbstractDeviceController deviceController;
private DeviceSelectionMenuController mainMenuController;
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
        //this line is used for testing
        System.out.println("Message received: " + msg.toString());

           //check message type
        switch (((AbstractMessage)msg).getType()){
            case 1:
                System.out.println("Device details received.");
                //device details received
                deviceController.update((AbstractDeviceMessage)msg);
                break;
            case 2:
                //new device received
                mainMenuController.addNewDevice((NewDeviceMessage)msg);
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
        //c is the controller for the device
        deviceController = c;
        NewDeviceMessage msg = new NewDeviceMessage(i);
        Send(msg);
    }

    public void getDevices(DeviceSelectionMenuController deviceSelectionPaneController) {
        //request all devices from server, also gets client id
        mainMenuController = deviceSelectionPaneController;
        StartupMessage msg = new StartupMessage( -1);
        Send(msg);
    }

    public void UpdateServer(AbstractDeviceMessage msg){
        //just used by devices to update the server
        Send(msg);
    }

    private void Send(Object msg){
        //general send method, just used to clean up code
        try {
            sendToServer(msg);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
