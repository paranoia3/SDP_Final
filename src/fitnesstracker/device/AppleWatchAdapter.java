package fitnesstracker.device;

public class AppleWatchAdapter implements DeviceAdapter {

    private final VendorApi api;

    public AppleWatchAdapter(VendorApi api) {
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
