package fitnesstracker.command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {

    private final List<Command> history = new ArrayList<>();

    public void execute(Command command) {
        history.add(command);
        command.execute();
    }

    public List<Command> getHistory() {
        return new ArrayList<>(history);
    }
}