package fitnesstracker.observer;

public class HeartRateConsoleObserver implements HealthDataObserver {

    @Override
    public void onHealthDataUpdate(HealthData data) {
        System.out.println("[Dashboard] Heart rate: " + data.getHeartRate()
                + " bpm, steps: " + data.getSteps());
    }
}