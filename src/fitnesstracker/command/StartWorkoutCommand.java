package fitnesstracker.command;

public class StartWorkoutCommand implements Command {

    private final WorkoutSession session;

    public StartWorkoutCommand(WorkoutSession session) {
        this.session = session;
    }

    @Override
    public void execute() {
        session.startSession();
    }
}