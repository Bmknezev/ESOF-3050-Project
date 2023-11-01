import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        SmartHomeClient s = new SmartHomeClient("127.0.0.1", 19920);
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        //create new light object
        light l = new light(100, 0.5f);

        try {
            s.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            s.sendToServer(l);
            System.out.println("Message 1 sent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    try{
        s.sendToServer("Hello");
        System.out.println("Message 2 sent");
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

        try {
            s.closeConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}