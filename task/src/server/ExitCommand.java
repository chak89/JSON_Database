package server;

public class ExitCommand implements Command{

    Database database;

    public ExitCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        this.database.exit();
    }
}
