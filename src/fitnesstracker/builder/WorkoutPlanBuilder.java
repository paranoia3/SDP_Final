package fitnesstracker.builder;

import fitnesstracker.model.WorkoutPlan;

public interface WorkoutPlanBuilder {
    void reset(String goal);
    void addWarmup(String name, int duration);
    void addCardio(String name, int duration);
    void addStrength(String name, int duration);
    void addCooldown(String name, int duration);
    WorkoutPlan build();
}
