package client;

import com.beust.jcommander.JCommander;
import server.JsonObject;

public class Main {

    public static void main(String[] args) {
        ArgsCmd argsCmd = new ArgsCmd();
        JCommander jCmd = JCommander.newBuilder()
                .addObject(argsCmd)
                .build();
        jCmd.parse(args);

        MainClient mainClient = new MainClient();
        mainClient.startUp(argsCmd);
    }
}