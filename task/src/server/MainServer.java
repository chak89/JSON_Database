package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 34522;
    private ServerSocket server;
    private Database database;
    private Controller controller;
    private String wloop;

    public MainServer(Database database, Controller controller) {
        this.database = database;
        this.controller = controller;

        try {
            server = new ServerSocket(SERVER_PORT, 5, InetAddress.getByName(SERVER_ADDRESS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        System.out.println("Server started!");
        this.wloop = "";
        while (!this.wloop.equals("exit")) {
            try (
                    Socket socket = this.server.accept(); // accepting a new client
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream())
            ) {
                String receivedMsg = input.readUTF(); // reading a message

                String returnedData = receivedData(receivedMsg);

                output.writeUTF(returnedData); // resend it to the client

                if (this.wloop.equals("exit")) {
                    this.server.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String receivedData(String receivedMsg) {
        String[] split = receivedMsg.split(" ", 3);

        switch (split[0]) {
            case "get":
                GetCommand getCommand = new GetCommand(this.database);
                getCommand.setIndex(Integer.parseInt(split[1]));
                this.controller.setCommand(getCommand);
                return this.controller.executeCommand();
            case "set":
                SetCommand setCommand = new SetCommand(this.database);
                setCommand.setIndex(Integer.parseInt(split[1]));
                setCommand.setValue(split[2]);
                this.controller.setCommand(setCommand);
                return this.controller.executeCommand();
            case "delete":
                DeleteCommand deleteCommand = new DeleteCommand(this.database);
                deleteCommand.setIndex(Integer.parseInt(split[1]));
                this.controller.setCommand(deleteCommand);
                return this.controller.executeCommand();
            case "exit":
                this.wloop = "exit";
                return "OK";
        }
        return "";
    }

}
