package server.commands;

import com.google.gson.JsonElement;
import server.Command;
import server.Database;

public class DeleteCommand implements Command {

    Database database;
    JsonElement key;

    public DeleteCommand(Database database) {
        this.database = database;
    }

    public void setKey(JsonElement key) {
        this.key = key;
    }

    @Override
    public void execute() {
        this.database.delCell(this.key);
    }
}
