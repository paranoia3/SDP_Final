package fitnesstracker.facade;

public class FitbitApi {

    public void connect() {
        System.out.println("Connecting to Fitbit API...");
    }

    public int readHeartRate() {
        return 130;
    }

    public int readSteps() {
        return 1200;
    }
}