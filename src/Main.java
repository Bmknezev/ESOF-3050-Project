import java.io.IOException;

public class Main {
    public static void main(String[] args) {

    SmartHomeClient s = new SmartHomeClient("127.0.0.1", 19920);
    //msg m = new msg(4);
        try {
            s.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String msg = "test";

        try {
            s.sendToServer(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}