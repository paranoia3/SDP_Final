package fitnesstracker.device;

public class FitbitApi implements VendorApi {

    @Override
    public int readRawHeartRate() {
        return 125;
    }

    @Override
    public int readRawSteps() {
        return 1500;
    }
}
