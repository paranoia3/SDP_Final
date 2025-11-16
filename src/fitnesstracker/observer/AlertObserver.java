package fitnesstracker.observer;

import fitnesstracker.UserConfig;

public class AlertObserver implements HealthDataObserver {

    private final UserConfig config;

    public AlertObserver(UserConfig config) {
        this.config = config;
    }

    @Override
    public void onHealthDataUpdate(HealthData data) {
        if (data.getHeartRate() > config.getAlertHeartRate()) {
            System.out.println("[ALERT] Heart rate is too high! (" +
                    data.getHeartRate() + " bpm, threshold: " +
                    config.getAlertHeartRate() + ")");
        }
    }
}
