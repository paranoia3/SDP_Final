package fitnesstracker.strategy;

import fitnesstracker.builder.WorkoutPlanBuilder;

public class MuscleGainStrategy implements IntensityStrategy {

    @Override
    public String getName() {
        return "Muscle Gain Plan";
    }

    @Override
    public void buildWorkout(WorkoutPlanBuilder builder) {
        builder.addWarmup("Dynamic warmup", 10);
        builder.addStrength("Upper body (push)", 20);
        builder.addStrength("Lower body (legs)", 20);
        builder.addStrength("Core training", 15);
        builder.addCooldown("Foam rolling", 10);
    }
}
