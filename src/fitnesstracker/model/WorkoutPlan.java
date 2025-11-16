package fitnesstracker.model;

import java.util.ArrayList;
import java.util.List;

public class WorkoutPlan {
    private final String goal;
    private final List<Exercise> exercises = new ArrayList<>();

    public WorkoutPlan(String goal) {
        this.goal = goal;
    }

    public String getGoal() {
        return goal;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public int getTotalDuration() {
        int total = 0;
        for (Exercise e : exercises) {
            total += e.getDurationMinutes();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Goal: ").append(goal).append("\n");
        sb.append("Total duration: ").append(getTotalDuration()).append(" min\n");
        sb.append("Exercises:\n");
        for (Exercise e : exercises) {
            sb.append(" - ").append(e).append("\n");
        }
        return sb.toString();
    }
}
