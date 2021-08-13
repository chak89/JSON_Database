package server;

public class DeleteCommand implements Command{

    Database database;
    String key;

    public DeleteCommand(Database database) {
        this.database = database;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void execute() {
        this.database.delCell(this.key);
    }
}
