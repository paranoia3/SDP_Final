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
    public void addWarmup(String name, int duration) {
        plan.addExercise(new Exercise(name, ExerciseType.WARMUP, duration, "Warm-up exercise"));
    }

    @Override
    public void addCardio(String name, int duration) {
        plan.addExercise(new Exercise(name, ExerciseType.CARDIO, duration, "Cardio exercise"));
    }

    @Override
    public void addStrength(String name, int duration) {
        plan.addExercise(new Exercise(name, ExerciseType.STRENGTH, duration, "Strength exercise"));
    }

    @Override
    public void addCooldown(String name, int duration) {
        plan.addExercise(new Exercise(name, ExerciseType.COOLDOWN, duration, "Cooldown exercise"));
    }

    @Override
    public WorkoutPlan build() {
        return plan;
    }
}
