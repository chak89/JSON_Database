package server;

public class GetCommand implements Command{

    Database database;
    int index;

    public GetCommand(Database database) {
        this.database = database;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String execute() {
        return this.database.getCell(this.index);
    }
}
