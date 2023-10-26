import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

    SmartHomeClient s = new SmartHomeClient("127.0.0.1", 19920);
    //msg m = new msg(4);
        s.openConnection();
        String msg = "test";

        s.sendToServer(msg);


    }
}