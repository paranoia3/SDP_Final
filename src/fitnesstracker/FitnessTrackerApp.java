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

import java.util.Scanner;

public class FitnessTrackerApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Singleton config
        TrackerConfig config = TrackerConfig.getInstance();

        System.out.print("Enter your name: ");
        config.setUserName(sc.nextLine());

        System.out.print("Enter your maximum heart rate: ");
        config.setMaxHeartRate(sc.nextInt());

        System.out.print("Enter the alarm threshold (pulse): ");
        config.setAlertHeartRate(sc.nextInt());
        sc.nextLine();

        System.out.println("\nSelect a training goal:");
        System.out.println("1 - Fat Loss");
        System.out.println("2 - Muscle Gain");
        System.out.println("3 - Endurance");

        int choice = sc.nextInt();
        sc.nextLine();

        IntensityStrategy strategy;

        switch (choice) {
            case 2 -> strategy = new MuscleGainStrategy();
            case 3 -> strategy = new EnduranceStrategy();
            default -> strategy = new FatLossStrategy();
        }

        // Создание плана тренировки
        WorkoutPlanBuilder builder = new SimpleWorkoutPlanBuilder();
        WorkoutDirector director = new WorkoutDirector(strategy);
        WorkoutPlan plan = director.createPlan(builder);

        System.out.println("\n=== Your workout plan ===");
        System.out.println(plan);

        System.out.println("\nSelect device:");
        System.out.println("1 - Fitbit");
        System.out.println("2 - Apple Watch");
        System.out.println("3 - Generic Band");

        int devChoice = sc.nextInt();
        sc.nextLine();

        DeviceType type = switch (devChoice) {
            case 2 -> DeviceType.APPLE_WATCH;
            case 3 -> DeviceType.GENERIC_BAND;
            default -> DeviceType.FITBIT;
        };

        DeviceApiFacade facade = new DeviceApiFacade();
        facade.connect(type);

        // Настройка Observer
        WearableDevice device = new WearableDevice("Your Device", facade, config);
        device.attach(new HeartRateConsoleObserver());
        device.attach(new AlertObserver());

        // Command + Session
        WorkoutSession session = new WorkoutSession(plan);
        CommandInvoker invoker = new CommandInvoker();

        System.out.println("\nPress Enter to start training...");
        sc.nextLine();
        invoker.execute(new StartWorkoutCommand(session));

        // Симуляция 3 обновлений
        for (int i = 0; i < 3; i++) {
            System.out.println("\nPress Enter to update device data...");
            sc.nextLine();
            device.pullData(type);
        }

        // Лог упражнений
        System.out.print("\nEnter exercise name: ");
        String exName = sc.nextLine();

        System.out.print("Enter the number of repetitions/minutes: ");
        int reps = sc.nextInt();
        sc.nextLine();

        invoker.execute(new LogExerciseCommand(session, exName, reps));

        System.out.println("\nPress Enter to end the workout...");
        sc.nextLine();

        invoker.execute(new StopWorkoutCommand(session));

        System.out.println("\n🎉 Workout complete! Thank you for using Fitness Tracker.");
    }
}