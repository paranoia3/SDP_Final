package fitnesstracker.config;

public class TrackerConfig {

    private static TrackerConfig instance;

    private String userName;
    private int maxHeartRate;
    private int alertHeartRate;

    private TrackerConfig() {
        this.userName = "Anonymous";
        this.maxHeartRate = 190;
        this.alertHeartRate = 170;
    }

    public static synchronized TrackerConfig getInstance() {
        if (instance == null) {
            instance = new TrackerConfig();
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public TrackerConfig setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public int getMaxHeartRate() {
        return maxHeartRate;
    }

    public TrackerConfig setMaxHeartRate(int maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
        return this;
    }

    public int getAlertHeartRate() {
        return alertHeartRate;
    }

    public TrackerConfig setAlertHeartRate(int alertHeartRate) {
        this.alertHeartRate = alertHeartRate;
        return this;
    }
}