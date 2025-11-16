package fitnesstracker;

import fitnesstracker.builder.SimpleWorkoutPlanBuilder;
import fitnesstracker.builder.WorkoutDirector;
import fitnesstracker.builder.WorkoutPlanBuilder;
import fitnesstracker.command.*;
import fitnesstracker.config.TrackerConfig;
import fitnesstracker.facade.DeviceApiFacade;
import fitnesstracker.facade.DeviceType;
import fitnesstracker.model.WorkoutPlan;
import fitnesstracker.observer.AlertObserver;
import fitnesstracker.observer.HeartRateConsoleObserver;
import fitnesstracker.observer.WearableDevice;
import fitnesstracker.strategy.*;
import java.util.List;
import java.util.Scanner;

public class FitnessTrackerController {

    private final Scanner sc = new Scanner(System.in);
    private final TrackerConfig config = TrackerConfig.getInstance();
    private final DeviceApiFacade facade = new DeviceApiFacade();
    private final CommandInvoker invoker = new CommandInvoker();

    private WorkoutPlan currentPlan;
    private WorkoutSession currentSession;
    private WearableDevice currentDevice;
    private DeviceType currentDeviceType;

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
                case 7 -> showHistory();
                case 8 -> {
                    System.out.println("\nExiting the application. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Unknown menu item. Try again.");
            }
        }

        sc.close();
    }

    // --- Initialization (Singleton) ---
    private void initUserConfig() {
        System.out.println("=== Welcome to Fitness Tracker ===");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        config.setUserName(name);

        int maxHR = readInt("Enter your maximum heart rate (e.g., 190): ");
        config.setMaxHeartRate(maxHR);

        int alertHR = readInt("Enter alert heart rate threshold (e.g., 170): ");
        config.setAlertHeartRate(alertHR);

        System.out.println("\nSettings saved for user: " + config.getUserName());
    }

    // --- Main Menu ---
    private void printMenu() {
        System.out.println("\n=== Fitness Tracker Menu ===");
        System.out.println("1. Create workout plan (Builder + Strategy)");
        System.out.println("2. Select device (Facade + Observer)");
        System.out.println("3. Start workout (Command)");
        System.out.println("4. Update device data (Observer)");
        System.out.println("5. Log exercise (Command)");
        System.out.println("6. Stop workout (Command)");
        System.out.println("7. Show command history");
        System.out.println("8. Exit");
    }

    // --- 1. Create workout plan (Builder + Strategy) ---
    private void createWorkoutPlan() {
        System.out.println("\n=== Create Workout Plan ===");
        System.out.println("Choose your goal:");
        System.out.println("1 - Fat Loss");
        System.out.println("2 - Muscle Gain");
        System.out.println("3 - Endurance");

        int choice = readInt("Your choice: ");

        IntensityStrategy strategy = switch (choice) {
            case 2 -> new MuscleGainStrategy();
            case 3 -> new EnduranceStrategy();
            default -> new FatLossStrategy();
        };

        WorkoutPlanBuilder builder = new SimpleWorkoutPlanBuilder();
        WorkoutDirector director = new WorkoutDirector(strategy);
        currentPlan = director.createPlan(builder);

        System.out.println("\nWorkout plan created:");
        System.out.println(currentPlan);

        currentSession = new WorkoutSession(currentPlan);
        System.out.println("Workout session is ready to start.");
    }

    // --- 2. Select device (Facade + Observer) ---
    private void selectDevice() {
        System.out.println("\n=== Select Device ===");
        System.out.println("1 - Fitbit");
        System.out.println("2 - Apple Watch");
        System.out.println("3 - Generic Band");

        int devChoice = readInt("Your choice: ");

        currentDeviceType = switch (devChoice) {
            case 2 -> DeviceType.APPLE_WATCH;
            case 3 -> DeviceType.GENERIC_BAND;
            default -> DeviceType.FITBIT;
        };

        facade.connect(currentDeviceType);

        currentDevice = new WearableDevice("User Device", facade, config);
        currentDevice.attach(new HeartRateConsoleObserver());
        currentDevice.attach(new AlertObserver());

        System.out.println("Device selected. Observers attached successfully.");
    }

    // --- 3. Start workout (Command) ---
    private void startWorkout() {
        if (currentPlan == null) {
            System.out.println("Please create a workout plan first (option 1).");
            return;
        }
        if (currentSession == null) {
            currentSession = new WorkoutSession(currentPlan);
        }

        Command startCommand = new StartWorkoutCommand(currentSession);
        invoker.execute(startCommand);
    }

    // --- 4. Update device data (Observer + Facade) ---
    private void updateDeviceData() {
        if (currentDevice == null || currentDeviceType == null) {
            System.out.println("Please select a device first (option 2).");
            return;
        }

        System.out.println("\nUpdating device data...");
        currentDevice.pullData(currentDeviceType);
    }

    // --- 5. Log exercise (Command) ---
    private void logExercise() {
        if (currentSession == null || !currentSession.isActive()) {
            System.out.println("Start a workout session first (option 3).");
            return;
        }

        System.out.print("Enter exercise name: ");
        String name = sc.nextLine();

        int repsOrMinutes = readInt("Enter number of reps/minutes: ");

        Command logCommand = new LogExerciseCommand(currentSession, name, repsOrMinutes);
        invoker.execute(logCommand);
    }

    // --- 6. Stop workout (Command) ---
    private void stopWorkout() {
        if (currentSession == null || !currentSession.isActive()) {
            System.out.println("Workout session is not active.");
            return;
        }

        Command stopCommand = new StopWorkoutCommand(currentSession);
        invoker.execute(stopCommand);
    }

    // --- 7. Show history (CommandInvoker) ---
    private void showHistory() {
        System.out.println("\n=== Command History ===");
        List<Command> history = invoker.getHistory();
        if (history.isEmpty()) {
            System.out.println("History is empty.");
            return;
        }
        int i = 1;
        for (Command c : history) {
            System.out.println(i + ". " + c.getClass().getSimpleName());
            i++;
        }
    }

    // --- Input helper ---
    private int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                String line = sc.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
