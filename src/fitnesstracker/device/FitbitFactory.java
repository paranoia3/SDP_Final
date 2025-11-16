package fitnesstracker.device;

import fitnesstracker.UserConfig;
import fitnesstracker.observer.WearableDevice;

public class FitbitFactory implements AbstractDeviceFactory {

    @Override
    public WearableDevice createWearableDevice(UserConfig config) {
        VendorApi api = new FitbitApi();
        DeviceAdapter adapter = new FitbitAdapter(api);
        return new WearableDevice("Fitbit", adapter, config);
    }
}
