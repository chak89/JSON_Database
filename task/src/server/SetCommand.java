package server;

public class SetCommand implements Command {

    Database database;
    String key;
    String value;

    public SetCommand(Database database) {
        this.database = database;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void execute() {
        this.database.setCell(this.key, this.value);
    }
}
