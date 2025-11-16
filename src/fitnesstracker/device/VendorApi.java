package fitnesstracker.device;

public interface VendorApi {
    int readRawHeartRate();
    int readRawSteps();
}
