package fitnesstracker;

public class UserConfig {
    private String userName;
    private int maxHeartRate;
    private int alertHeartRate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMaxHeartRate() {
        return maxHeartRate;
    }

    public void setMaxHeartRate(int maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }

    public int getAlertHeartRate() {
        return alertHeartRate;
    }

    public void setAlertHeartRate(int alertHeartRate) {
        this.alertHeartRate = alertHeartRate;
    }
}
