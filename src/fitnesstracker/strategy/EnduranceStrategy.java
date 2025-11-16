package fitnesstracker.strategy;

import fitnesstracker.builder.WorkoutPlanBuilder;

public class EnduranceStrategy implements IntensityStrategy {

    @Override
    public String getName() {
        return "Endurance Plan";
    }

    @Override
    public void buildWorkout(WorkoutPlanBuilder builder) {
        builder.addWarmup("Brisk walking", 10);
        builder.addCardio("Long steady run", 30);
        builder.addCardio("Cycling", 20);
        builder.addCooldown("Light stretching", 10);
    }
}
