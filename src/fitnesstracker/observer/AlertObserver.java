package fitnesstracker.observer;

import fitnesstracker.config.TrackerConfig;

public class AlertObserver implements HealthDataObserver {

    private final TrackerConfig config = TrackerConfig.getInstance();

    @Override
    public void onHealthDataUpdate(HealthData data) {
        if (data.getHeartRate() > config.getAlertHeartRate()) {
            System.out.println("[ALERT] Heart rate is too high! Please slow down.");
        }
    }
}
