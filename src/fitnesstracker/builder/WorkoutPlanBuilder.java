package fitnesstracker.builder;

import fitnesstracker.model.WorkoutPlan;

public interface WorkoutPlanBuilder {

    void reset(String goal);

    void addWarmup(String name, int durationMinutes);

    void addCardio(String name, int durationMinutes);

    void addStrength(String name, int durationMinutes);

    void addCooldown(String name, int durationMinutes);

    WorkoutPlan build();
}
