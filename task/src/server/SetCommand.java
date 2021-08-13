package server;

public class SetCommand implements Command {

    Database database;
    int index;
    String value;

    public SetCommand(Database database) {
        this.database = database;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String execute() {
        return this.database.setCell(this.index, this.value);
    }
}
