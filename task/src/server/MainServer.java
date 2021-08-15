package server;

import server.commands.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServer {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 34522;
    private final Database database;
    private final Controller controller;
    private String wloop;
    private final ExecutorService executors;


    public MainServer(Database database, Controller controller) {
        this.database = database;
        this.controller = controller;
        this.executors = Executors.newFixedThreadPool(4);
    }

    public void startServer() {
        System.out.println("Server started!");
        this.wloop = "";

        // create serverSocket once for all connections
        try (ServerSocket server = new ServerSocket(SERVER_PORT, 5, InetAddress.getByName(SERVER_ADDRESS))) {
            while (!this.wloop.equals("exit")) {

                // accept a client connection, not in a try-with-resources so this will have to be explicitly closed
                final Socket socket = server.accept();

                executors.submit(() -> {
                    // limit scope of input/output to where they're actually used
                    try (DataInputStream input = new DataInputStream(socket.getInputStream());
                         DataOutputStream output = new DataOutputStream(socket.getOutputStream())
                    ) {

                        String receivedMsg = input.readUTF(); // reading a message
                        receivedData(receivedMsg);
                        String returnedData = convertToJsonString();
                        output.writeUTF(returnedData); // resend it to the client

                        if (this.wloop.equals("exit")) {
                            this.executors.shutdownNow();
                            server.close();
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

            }
        } catch (IOException ignored) {
        }
    }

    private String convertToJsonString() {
        return this.database.getJsonObjectResponse().getSerializeJSON();
    }

    private void receivedData(String receivedMsg) {
        ReceivedJsonObject receivedJsonObject = new ReceivedJsonObject(receivedMsg).getDeserializeJSON();

        switch (receivedJsonObject.getType()) {
            case "get":
                GetCommand getCommand = new GetCommand(this.database);
                getCommand.setKey(receivedJsonObject.getKey());
                this.controller.setCommand(getCommand);
                this.controller.executeCommand();
                break;
            case "set":
                SetCommand setCommand = new SetCommand(this.database);
                setCommand.setKey(receivedJsonObject.getKey());
                setCommand.setValue(receivedJsonObject.getValue());
                this.controller.setCommand(setCommand);
                this.controller.executeCommand();
                break;
            case "delete":
                DeleteCommand deleteCommand = new DeleteCommand(this.database);
                deleteCommand.setKey(receivedJsonObject.getKey());
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
