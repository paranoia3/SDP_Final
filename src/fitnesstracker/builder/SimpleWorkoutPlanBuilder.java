package fitnesstracker.builder;

import fitnesstracker.model.Exercise;
import fitnesstracker.model.ExerciseType;
import fitnesstracker.model.WorkoutPlan;

public class SimpleWorkoutPlanBuilder implements WorkoutPlanBuilder {

    private WorkoutPlan plan;

    @Override
    public void reset(String goal) {
        this.plan = new WorkoutPlan(goal);
    }

    @Override
    public void addWarmup(String name, int durationMinutes) {
        plan.addExercise(new Exercise(name, ExerciseType.WARMUP, durationMinutes,
                "Light warmup to prepare your body."));
    }

    @Override
    public void addCardio(String name, int durationMinutes) {
        plan.addExercise(new Exercise(name, ExerciseType.CARDIO, durationMinutes,
                "Cardio to improve endurance and burn calories."));
    }

    @Override
    public void addStrength(String name, int durationMinutes) {
        plan.addExercise(new Exercise(name, ExerciseType.STRENGTH, durationMinutes,
                "Strength training to build muscle."));
    }

    @Override
    public void addCooldown(String name, int durationMinutes) {
        plan.addExercise(new Exercise(name, ExerciseType.COOLDOWN, durationMinutes,
                "Cooldown to relax muscles and lower heart rate."));
    }

    @Override
    public WorkoutPlan build() {
        return plan;
    }
}