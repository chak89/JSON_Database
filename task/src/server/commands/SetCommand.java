package server.commands;

import com.google.gson.JsonElement;
import server.Command;
import server.Database;

public class SetCommand implements Command {

    Database database;
    JsonElement key;
    JsonElement value;

    public SetCommand(Database database) {
        this.database = database;
    }

    public void setKey(JsonElement key) {
        this.key = key;
    }

    public void setValue(JsonElement value) {
        this.value = value;
    }

    @Override
    public void execute() {
        this.database.setCell(this.key, this.value);
    }
}
