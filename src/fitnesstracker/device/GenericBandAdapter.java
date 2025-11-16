package fitnesstracker.device;

public class GenericBandAdapter implements DeviceAdapter {

    private final VendorApi api;

    public GenericBandAdapter(VendorApi api) {
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
