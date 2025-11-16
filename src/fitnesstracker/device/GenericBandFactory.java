package fitnesstracker.device;

import fitnesstracker.UserConfig;
import fitnesstracker.observer.WearableDevice;

public class GenericBandFactory implements AbstractDeviceFactory {

    @Override
    public WearableDevice createWearableDevice(UserConfig config) {
        VendorApi api = new GenericBandApi();
        DeviceAdapter adapter = new GenericBandAdapter(api);
        return new WearableDevice("Generic Band", adapter, config);
    }
}
