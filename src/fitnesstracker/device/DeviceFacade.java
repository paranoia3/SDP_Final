package fitnesstracker.device;

import fitnesstracker.UserConfig;
import fitnesstracker.observer.HealthData;
import fitnesstracker.observer.WearableDevice;

public class DeviceFacade {

    private final AbstractDeviceFactory factory;
    private final UserConfig config;

    public DeviceFacade(AbstractDeviceFactory factory, UserConfig config) {
        this.factory = factory;
        this.config = config;
    }

    public WearableDevice createConfiguredDevice() {
        WearableDevice device = factory.createWearableDevice(config);
        System.out.println("Connected to device: " + device.getName());
        return device;
    }

    public void pullAndNotify(WearableDevice device) {
        HealthData data = device.pullData();
        device.notifyObservers(data);
    }
}
