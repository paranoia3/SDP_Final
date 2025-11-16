package fitnesstracker.model;

public class Exercise {

    private final String name;
    private final ExerciseType type;
    private final int durationMinutes;
    private final String description;

    public Exercise(String name, ExerciseType type, int durationMinutes, String description) {
        this.name = name;
        this.type = type;
        this.durationMinutes = durationMinutes;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public ExerciseType getType() {
        return type;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return type + " - " + name + " (" + durationMinutes + " min) : " + description;
    }
}