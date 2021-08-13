package server;

public class DeleteCommand implements Command{

    Database database;
    int index;

    public DeleteCommand(Database database) {
        this.database = database;
        this.index = index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String execute() {
        return this.database.delCell(this.index);
    }
}
