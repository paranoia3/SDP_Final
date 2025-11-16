package fitnesstracker.observer;

import fitnesstracker.config.TrackerConfig;
import fitnesstracker.facade.DeviceApiFacade;
import fitnesstracker.facade.DeviceType;
import java.util.ArrayList;
import java.util.List;

public class WearableDevice implements HealthDataSubject {

    private final String name;
    private final DeviceApiFacade facade;
    private final TrackerConfig config;

    private final List<HealthDataObserver> observers = new ArrayList<>();

    public WearableDevice(String name, DeviceApiFacade facade, TrackerConfig config) {
        this.name = name;
        this.facade = facade;
        this.config = config;
    }

    @Override
    public void attach(HealthDataObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(HealthDataObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(HealthData data) {
        for (HealthDataObserver observer : observers) {
            observer.onHealthDataUpdate(data);
        }
    }

    public void pullData(DeviceType type) {
        int hr = facade.readHeartRate(type);
        int steps = facade.readSteps(type);
        HealthData data = new HealthData(hr, steps);
        System.out.println("\n[" + name + "] New data from device: HR=" + hr + " bpm, steps=" + steps);
        notifyObservers(data);
    }

    public TrackerConfig getConfig() {
        return config;
    }

    public String getName() {
        return name;
    }
}