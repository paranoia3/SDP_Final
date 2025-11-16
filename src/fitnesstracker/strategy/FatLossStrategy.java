package fitnesstracker.strategy;

import fitnesstracker.builder.WorkoutPlanBuilder;

public class FatLossStrategy implements IntensityStrategy {

    @Override
    public String getName() {
        return "Fat Loss Plan";
    }

    @Override
    public void buildWorkout(WorkoutPlanBuilder builder) {
        builder.addWarmup("Joint mobility", 5);
        builder.addCardio("Jogging", 20);
        builder.addCardio("Jump rope", 10);
        builder.addStrength("Bodyweight circuit", 15);
        builder.addCooldown("Stretching", 10);
    }
}