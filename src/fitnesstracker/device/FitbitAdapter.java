package fitnesstracker.device;

public class FitbitAdapter implements DeviceAdapter {

    private final VendorApi api;

    public FitbitAdapter(VendorApi api) {
        this.api = api;
    }

    @Override
    public int getHeartRate() {
        return api.readRawHeartRate();
    }

    @Override
    public int getSteps() {
        return api.readRawSteps();
    }
}
