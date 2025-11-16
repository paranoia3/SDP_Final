package fitnesstracker;

import fitnesstracker.builder.SimpleWorkoutPlanBuilder;
import fitnesstracker.builder.WorkoutDirector;
import fitnesstracker.builder.WorkoutPlanBuilder;
import fitnesstracker.device.*;
import fitnesstracker.model.WorkoutPlan;
import fitnesstracker.observer.AlertObserver;
import fitnesstracker.observer.HealthData;
import fitnesstracker.observer.HealthDataObserver;
import fitnesstracker.observer.WearableDevice;
import fitnesstracker.strategy.*;

import java.util.Scanner;

public class FitnessTrackerController {

    private final Scanner sc = new Scanner(System.in);

    private final UserConfig userConfig = new UserConfig();

    private WorkoutPlan currentPlan;

    private DeviceFacade deviceFacade;
    private WearableDevice currentDevice;

    private boolean workoutActive = false;

    public void run() {
        initUserConfig();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1 -> createWorkoutPlan();
                case 2 -> selectDevice();
                case 3 -> startWorkout();
                case 4 -> updateDeviceData();
                case 5 -> logExercise();
                case 6 -> stopWorkout();
                case 7 -> running = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        System.out.println("Exiting Fitness Tracker. Goodbye!");
        sc.close();
    }

    private void initUserConfig() {
        System.out.println("=== Welcome to Fitness Tracker ===");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        userConfig.setUserName(name);

        int maxHR = readInt("Enter your maximum heart rate (e.g., 190): ");
        userConfig.setMaxHeartRate(maxHR);

        int alertHR = readInt("Enter alert heart rate threshold (e.g., 170): ");
        userConfig.setAlertHeartRate(alertHR);

        System.out.println("Settings saved for user: " + userConfig.getUserName());
        System.out.println();
    }

    private void printMenu() {
        System.out.println("==== MAIN MENU ====");
        System.out.println("1. Create workout plan (Builder + Strategy)");
        System.out.println("2. Select device (Abstract Factory + Adapter + Facade + Observer)");
        System.out.println("3. Start workout (simple flag)");
        System.out.println("4. Update device data (Observer notifications)");
        System.out.println("5. Log exercise (simple output)");
        System.out.println("6. Stop workout");
        System.out.println("7. Exit");
    }

    private int readInt(String msg) {
        while (true) {
            System.out.print(msg);
            String line = sc.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // === Builder + Strategy ===
    private void createWorkoutPlan() {
        System.out.println("=== Create Workout Plan ===");
        System.out.println("Choose your goal:");
        System.out.println("1 - Fat Loss");
        System.out.println("2 - Muscle Gain");
        System.out.println("3 - Endurance");

        int choice = readInt("Your choice: ");

        IntensityStrategy strategy;
        switch (choice) {
            case 2 -> strategy = new MuscleGainStrategy();
            case 3 -> strategy = new EnduranceStrategy();
            default -> strategy = new FatLossStrategy();
        }

        WorkoutPlanBuilder builder = new SimpleWorkoutPlanBuilder();
        WorkoutDirector director = new WorkoutDirector(strategy);

        currentPlan = director.createPlan(builder);

        System.out.println("Workout plan created:");
        System.out.println(currentPlan);
        System.out.println();

        workoutActive = false;
    }

    // === Abstract Factory + Adapter + Facade + Observer ===
    private void selectDevice() {
        System.out.println("=== Select Device ===");
        System.out.println("1 - Fitbit");
        System.out.println("2 - Apple Watch");
        System.out.println("3 - Generic Band");

        int choice = readInt("Your choice: ");

        AbstractDeviceFactory factory;
        switch (choice) {
            case 2 -> factory = new AppleWatchFactory();
            case 3 -> factory = new GenericBandFactory();
            default -> factory = new FitbitFactory();
        }

        // Фасад создаёт и настраивает устройство через фабрику и адаптеры
        deviceFacade = new DeviceFacade(factory, userConfig);

        currentDevice = deviceFacade.createConfiguredDevice();

        // Подключаем наблюдателей
        HealthDataObserver consoleObserver = data ->
                System.out.println("[Dashboard] Heart rate: " + data.getHeartRate()
                        + " bpm, steps: " + data.getSteps());
        HealthDataObserver alertObserver = new AlertObserver(userConfig);

        currentDevice.attach(consoleObserver);
        currentDevice.attach(alertObserver);

        System.out.println("Device selected and observers attached.\n");
    }

    private void startWorkout() {
        if (currentPlan == null) {
            System.out.println("Please create a workout plan first.");
            return;
        }
        if (workoutActive) {
            System.out.println("Workout is already active.");
            return;
        }
        workoutActive = true;
        System.out.println("Workout started with plan: " + currentPlan.getGoal());
    }

    private void updateDeviceData() {
        if (currentDevice == null) {
            System.out.println("Please select a device first.");
            return;
        }
        System.out.println("Updating device data...");
        deviceFacade.pullAndNotify(currentDevice);
        System.out.println();
    }

    private void logExercise() {
        if (!workoutActive) {
            System.out.println("Workout is not active. Start workout first.");
            return;
        }
        System.out.print("Enter exercise name: ");
        String name = sc.nextLine();
        int repsOrMinutes = readInt("Enter number of reps/minutes: ");

        System.out.println("[Workout] Logged exercise: "
                + name + " (" + repsOrMinutes + " reps/min)");
    }

    private void stopWorkout() {
        if (!workoutActive) {
            System.out.println("Workout is not active.");
            return;
        }
        workoutActive = false;
        System.out.println("Workout stopped. Good job!");
    }
}
