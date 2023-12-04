package ClientServer;


import GUI.Control.Abstract.AbstractDeviceController;
import GUI.Control.DeviceSelectionMenuController;
import GUI.Control.Interface.Updatable;
import GUI.Control.LoginMenuController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import messages.*;

public class SmartHomeClient extends ClientServer.AbstractClient {
private AbstractDeviceController deviceController;
private Updatable updatableDevice;
private DeviceSelectionMenuController mainMenuController;
private int currentDeviceID = -1;
private boolean admin;

private LoginMenuController loginMenuController;
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
        System.out.println("Message received from server.");


           //check message type
        switch (((AbstractMessage)msg).getType()){
            case 1:
                //device details received
                if(currentDeviceID == ((AbstractDeviceMessage)msg).getDeviceID())
                    updatableDevice.update((AbstractDeviceMessage)msg);
                break;
            case 2:
                //new device received
                if(((NewDeviceMessage)msg).getDeviceID() == -5 )
                    mainMenuController.clearList();
                else
                    mainMenuController.addNewDevice((NewDeviceMessage)msg);
                break;
            case 3:
                //client id received
                //clientID = ((StartupMessage)msg).getClientID();
                break;
            case 5:
                //login details received
                admin = ((LoginMessage)msg).getAdmin();
                mainMenuController.setWelcomeLabel(((LoginMessage)msg).getAdmin(),((LoginMessage) msg).getUsername());
                if (admin){
                    mainMenuController.enableAdminControls();
                }
                else{
                    mainMenuController.disableAdminControls();
                }
                loginMenuController.login((LoginMessage)msg);
                break;
            case 6:
                //PIN change response
                if(((PinMessage)msg).getNewPin() == -1){
                    System.out.println("PIN.");
                    deviceController.response((PinMessage) msg);
                    return;
                }

                if(((PinMessage)msg).getPinStatus()){
                    deviceController.setPIN(((PinMessage)msg).getNewPin());
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("PIN Change");
                        alert.setHeaderText("PIN Changed Successfully");
                        alert.setContentText("Your new PIN is: " + ((PinMessage)msg).getNewPin());
                        alert.showAndWait();
                    });
                }
                else{
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("PIN Change");
                        alert.setHeaderText("PIN Change Failed");
                        alert.setContentText("Your PIN was not changed. Please ensure you entered the correct PIN.");
                        alert.showAndWait();
                    });
                }
            case 7:
                //user list received
                if(((UserListMessage)msg).getNewUser())
                    mainMenuController.updateUserList((UserListMessage)msg);
                else
                    mainMenuController.error((UserListMessage)msg);
                break;
            default:
                System.out.println("Unknown message type received.");
                break;
        }
    }

    public void request(int i, AbstractDeviceController c) {
        //request a device from server with device id i
        //c is the controller for the device
        //deviceController = c;
        updatableDevice = (Updatable)c;
        setCurrentDeviceID(i);
        NewDeviceMessage msg = new NewDeviceMessage(i);
        Send(msg);
    }

    public void getDevices(DeviceSelectionMenuController deviceSelectionPaneController) {
        //request all devices from server, also gets client id
        mainMenuController = deviceSelectionPaneController;
        StartupMessage msg = new StartupMessage( -1);
        Send(msg);
    }

    public void getUsers(DeviceSelectionMenuController deviceSelectionPaneController) {
        //request all devices from server, also gets client id
        mainMenuController = deviceSelectionPaneController;
        Send(new UserListMessage(-1, "", "", false, false));
    }

    public void UpdateServer(AbstractMessage msg){
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

    public void setCurrentDeviceID(int id){
        currentDeviceID = id;
    }

    public void setLoginMenuController(LoginMenuController loginMenuController){
        this.loginMenuController = loginMenuController;
    }

    public void checkPIN(PinMessage msg, AbstractDeviceController c){
        deviceController = c;
        Send(msg);
    }

    @Override
    protected void connectionClosed() {
        Platform.runLater(() ->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Connection to server lost.");
            alert.setContentText("Please check your internet connection and ensure the server is still online.");
            alert.showAndWait();
            Platform.exit();
        });

    }
}
