package server.commands;

import com.google.gson.JsonElement;
import server.Command;
import server.Database;

public class GetCommand implements Command {

    Database database;
    JsonElement key;

    public GetCommand(Database database) {
        this.database = database;
    }

    public void setKey(JsonElement key) {
        this.key = key;
    }

    @Override
    public void execute() {
        this.database.getCell(this.key);
    }
}
