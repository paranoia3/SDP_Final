package fitnesstracker.model;

import java.util.ArrayList;
import java.util.List;

public class WorkoutPlan {

    private final String goal;
    private final List<Exercise> exercises = new ArrayList<>();

    public WorkoutPlan(String goal) {
        this.goal = goal;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public String getGoal() {
        return goal;
    }

    public List<Exercise> getExercises() {
        return new ArrayList<>(exercises);
    }

    public int getTotalDuration() {
        int sum = 0;
        for (Exercise e : exercises) {
            sum += e.getDurationMinutes();
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Goal: ")
                .append(goal)
                .append("\nTotal duration: ")
                .append(getTotalDuration())
                .append(" minutes\nExercises:\n");

        for (Exercise e : exercises) {
            sb.append(" - ").append(e).append("\n");
        }
        return sb.toString();
    }
}