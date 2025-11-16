package fitnesstracker.device;

public class GenericBandApi implements VendorApi {

    @Override
    public int readRawHeartRate() {
        return 118;
    }

    @Override
    public int readRawSteps() {
        return 900;
    }
}
