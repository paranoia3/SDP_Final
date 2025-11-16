package fitnesstracker.strategy;

import fitnesstracker.builder.WorkoutPlanBuilder;

public class EnduranceStrategy implements IntensityStrategy {

    @Override
    public String getName() {
        return "Endurance Plan";
    }

    @Override
    public void buildWorkout(WorkoutPlanBuilder builder) {
        builder.addWarmup("Light cycling", 10);
        builder.addCardio("Steady-state run", 30);
        builder.addCardio("Intervals on bike", 15);
        builder.addCooldown("Walking + breathing", 10);
    }
}