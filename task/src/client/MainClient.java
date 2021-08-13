package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class MainClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 34522;
    private Socket socket;

    public MainClient() {
        try {
            socket = new Socket(InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startClient() {
        System.out.println("Client started!");
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {

            String msg = "Give me a record # 12";
            output.writeUTF(msg);
            System.out.println("Sent: " + msg);
            String receivedMsg = input.readUTF();
            System.out.println("Received: " + receivedMsg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
