package fitnesstracker.facade;

public class GenericBandApi {

    public void connect() {
        System.out.println("Connecting to Generic Band API...");
    }

    public int readHeartRate() {
        return 110;
    }

    public int readSteps() {
        return 900;
    }
}
