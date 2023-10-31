import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        SmartHomeClient s = new SmartHomeClient("127.0.0.1", 19920);
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        try {
            s.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String msg = "connection established";

        while(!Objects.equals(msg, "/close")) {
            msg = scanner.nextLine();
            try {
                s.sendToServer(msg);
            } catch (IOException e) {
                    System.out.println("not connected to server");
            }

        }
        try {
            s.closeConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}