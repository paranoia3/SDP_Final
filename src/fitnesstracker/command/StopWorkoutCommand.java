package fitnesstracker.command;

public class StopWorkoutCommand implements Command {

    private final WorkoutSession session;

    public StopWorkoutCommand(WorkoutSession session) {
        this.session = session;
    }

    @Override
    public void execute() {
        session.stopSession();
    }
}