package fitnesstracker.device;

public class AppleWatchApi implements VendorApi {

    @Override
    public int readRawHeartRate() {
        return 132;
    }

    @Override
    public int readRawSteps() {
        return 1800;
    }
}
