package fitnesstracker.builder;

import fitnesstracker.model.WorkoutPlan;
import fitnesstracker.strategy.IntensityStrategy;

public class WorkoutDirector {

    private final IntensityStrategy strategy;

    public WorkoutDirector(IntensityStrategy strategy) {
        this.strategy = strategy;
    }

    public WorkoutPlan createPlan(WorkoutPlanBuilder builder) {
        builder.reset(strategy.getName());
        strategy.buildWorkout(builder);
        return builder.build();
    }
}