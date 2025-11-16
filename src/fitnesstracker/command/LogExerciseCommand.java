package fitnesstracker.command;

public class LogExerciseCommand implements Command {

    private final WorkoutSession session;
    private final String exerciseName;
    private final int repsOrMinutes;

    public LogExerciseCommand(WorkoutSession session, String exerciseName, int repsOrMinutes) {
        this.session = session;
        this.exerciseName = exerciseName;
        this.repsOrMinutes = repsOrMinutes;
    }

    @Override
    public void execute() {
        session.logExercise(exerciseName, repsOrMinutes);
    }
}