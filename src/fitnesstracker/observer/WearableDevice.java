package fitnesstracker.observer;

import fitnesstracker.device.DeviceAdapter;
import fitnesstracker.UserConfig;
import java.util.ArrayList;
import java.util.List;

public class WearableDevice {

    private final String name;
    private final DeviceAdapter adapter;
    private final UserConfig config;
    private final List<HealthDataObserver> observers = new ArrayList<>();

    public WearableDevice(String name, DeviceAdapter adapter, UserConfig config) {
        this.name = name;
        this.adapter = adapter;
        this.config = config;
    }

    public String getName() {
        return name;
    }

    public void attach(HealthDataObserver observer) {
        observers.add(observer);
    }

    public void detach(HealthDataObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(HealthData data) {
        for (HealthDataObserver o : observers) {
            o.onHealthDataUpdate(data);
        }
    }

    public HealthData pullData() {
        int hr = adapter.getHeartRate();
        int steps = adapter.getSteps();
        System.out.println("[" + name + "] New data: HR=" + hr + ", steps=" + steps);
        return new HealthData(hr, steps);
    }

    public UserConfig getConfig() {
        return config;
    }
}
