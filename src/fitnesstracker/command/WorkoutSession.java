package fitnesstracker.command;

import fitnesstracker.model.WorkoutPlan;

public class WorkoutSession {

    private final WorkoutPlan plan;
    private boolean active = false;

    public WorkoutSession(WorkoutPlan plan) {
        this.plan = plan;
    }

    public void startSession() {
        if (!active) {
            active = true;
            System.out.println("\n[WorkoutSession] Workout started: " + plan.getGoal());
        } else {
            System.out.println("[WorkoutSession] Workout already active.");
        }
    }

    public void stopSession() {
        if (active) {
            active = false;
            System.out.println("[WorkoutSession] Workout finished. Great job!");
        } else {
            System.out.println("[WorkoutSession] Workout is not active.");
        }
    }

    public void logExercise(String exerciseName, int repsOrMinutes) {
        System.out.println("[WorkoutSession] Logged exercise: " + exerciseName
                + " (" + repsOrMinutes + " reps/min)");
    }

    public boolean isActive() {
        return active;
    }
}
