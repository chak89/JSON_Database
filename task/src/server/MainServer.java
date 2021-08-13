package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainServer {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 34522;
    private ServerSocket server;

    public MainServer() {
        try {
            server = new ServerSocket(SERVER_PORT,5,InetAddress.getByName(SERVER_ADDRESS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        System.out.println("Server started!");
        try (
                Socket socket = this.server.accept(); // accepting a new client
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            String receivedMsg = input.readUTF(); // reading a message
            System.out.println("Received: " + receivedMsg);

            String sendMsg = receivedMsg.substring(10,receivedMsg.length());
            sendMsg = "A " + sendMsg + " was sent!";
            output.writeUTF(sendMsg); // resend it to the client
            System.out.println("Sent: " + sendMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
