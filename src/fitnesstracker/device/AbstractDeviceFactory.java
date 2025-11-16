package fitnesstracker.device;

import fitnesstracker.UserConfig;
import fitnesstracker.observer.WearableDevice;

public interface AbstractDeviceFactory {
    WearableDevice createWearableDevice(UserConfig config);
}
