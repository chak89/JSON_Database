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
    private final Database database;
    private final Controller controller;
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

                receivedData(receivedMsg);

                String returnedData = this.database.getJsonObjectResponse().getSerializeJSON();

                output.writeUTF(returnedData); // resend it to the client

                if (this.wloop.equals("exit")) {
                    this.server.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void receivedData(String receivedMsg) {
        JsonObject jsonObject = new JsonObject(receivedMsg).getDeserialzeJSON();

        switch (jsonObject.getType()) {
            case "get":
                GetCommand getCommand = new GetCommand(this.database);
                getCommand.setKey(jsonObject.getKey());
                this.controller.setCommand(getCommand);
                this.controller.executeCommand();
                break;
            case "set":
                SetCommand setCommand = new SetCommand(this.database);
                setCommand.setKey(jsonObject.getKey());
                setCommand.setValue(jsonObject.getValue());
                this.controller.setCommand(setCommand);
                this.controller.executeCommand();
                break;
            case "delete":
                DeleteCommand deleteCommand = new DeleteCommand(this.database);
                deleteCommand.setKey(jsonObject.getKey());
                this.controller.setCommand(deleteCommand);
                this.controller.executeCommand();
                break;
            case "exit":
                this.wloop = "exit";
                ExitCommand exitCommand = new ExitCommand(this.database);
                this.controller.setCommand(exitCommand);
                this.controller.executeCommand();
                break;
        }
    }
}
