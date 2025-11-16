package fitnesstracker.strategy;

import fitnesstracker.builder.WorkoutPlanBuilder;

public class FatLossStrategy implements IntensityStrategy {

    @Override
    public String getName() {
        return "Fat Loss Plan";
    }

    @Override
    public void buildWorkout(WorkoutPlanBuilder builder) {
        builder.addWarmup("Light jogging", 10);
        builder.addCardio("HIIT intervals", 20);
        builder.addCardio("Treadmill run", 15);
        builder.addCooldown("Stretching", 10);
    }
}
