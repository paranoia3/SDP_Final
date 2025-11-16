package fitnesstracker.observer;

public class HealthData {
    private final int heartRate;
    private final int steps;

    public HealthData(int heartRate, int steps) {
        this.heartRate = heartRate;
        this.steps = steps;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public int getSteps() {
        return steps;
    }
}
