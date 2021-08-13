package server;

import javax.xml.crypto.Data;

public class Main {

    public static void main(String[] args) {
        Database database = new Database();
        Controller controller = new Controller();
        MainServer mainServer = new MainServer(database,controller);
        mainServer.startServer();
    }
}
