package server;

public class GetCommand implements Command{

    Database database;
    String key;

    public GetCommand(Database database) {
        this.database = database;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void execute() {
        this.database.getCell(this.key);
    }
}
