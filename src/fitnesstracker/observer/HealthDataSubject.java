package fitnesstracker.observer;

public interface HealthDataSubject {

    void attach(HealthDataObserver observer);

    void detach(HealthDataObserver observer);

    void notifyObservers(HealthData data);
}
