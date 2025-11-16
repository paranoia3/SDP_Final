package fitnesstracker.device;

import fitnesstracker.UserConfig;
import fitnesstracker.observer.WearableDevice;

public class AppleWatchFactory implements AbstractDeviceFactory {

    @Override
    public WearableDevice createWearableDevice(UserConfig config) {
        VendorApi api = new AppleWatchApi();
        DeviceAdapter adapter = new AppleWatchAdapter(api);
        return new WearableDevice("Apple Watch", adapter, config);
    }
}
