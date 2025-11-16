package fitnesstracker.facade;

public class AppleWatchApi {

    public void connect() {
        System.out.println("Connecting to Apple Watch API...");
    }

    public int readHeartRate() {
        return 145;
    }

    public int readSteps() {
        return 1500;
    }
}