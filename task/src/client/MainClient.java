package client;

import server.JsonObject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class MainClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 34522;
    private Socket socket;
    private ArgsCmd argsCmd;

    public MainClient() {
        try {
            socket = new Socket(InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startUp(ArgsCmd argsCmd) {
        processArgs(argsCmd);
        startClient();
    }

    public void startClient() {
        System.out.println("Client started!");
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {

            String msg = serializeToJSON();
            output.writeUTF(msg);
            System.out.println("Sent: " + msg);

            String receivedMsg = input.readUTF();
            System.out.println("Received: " + receivedMsg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processArgs(ArgsCmd argsCmd) {
        this.argsCmd = argsCmd;
    }

    public String serializeToJSON() {
        JsonObject jsonObject = new JsonObject(argsCmd.getType(), argsCmd.getKey(), argsCmd.getStringValue());
        return jsonObject.getSerializeJSON();
    }
}
