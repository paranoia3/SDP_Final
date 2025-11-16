package fitnesstracker.facade;

public class DeviceApiFacade {

    private final FitbitApi fitbitApi = new FitbitApi();
    private final AppleWatchApi appleWatchApi = new AppleWatchApi();
    private final GenericBandApi genericBandApi = new GenericBandApi();

    public void connect(DeviceType type) {
        switch (type) {
            case FITBIT -> fitbitApi.connect();
            case APPLE_WATCH -> appleWatchApi.connect();
            case GENERIC_BAND -> genericBandApi.connect();
        }
    }

    public int readHeartRate(DeviceType type) {
        return switch (type) {
            case FITBIT -> fitbitApi.readHeartRate();
            case APPLE_WATCH -> appleWatchApi.readHeartRate();
            case GENERIC_BAND -> genericBandApi.readHeartRate();
        };
    }

    public int readSteps(DeviceType type) {
        return switch (type) {
            case FITBIT -> fitbitApi.readSteps();
            case APPLE_WATCH -> appleWatchApi.readSteps();
            case GENERIC_BAND -> genericBandApi.readSteps();
        };
    }
}