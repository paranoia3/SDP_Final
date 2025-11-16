package fitnesstracker.strategy;

import fitnesstracker.builder.WorkoutPlanBuilder;

public interface IntensityStrategy {
    String getName();
    void buildWorkout(WorkoutPlanBuilder builder);
}
