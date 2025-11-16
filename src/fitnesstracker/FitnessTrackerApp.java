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

        System.out.print("Введите ваше имя: ");
        config.setUserName(sc.nextLine());

        System.out.print("Введите допустимый максимальный пульс: ");
        config.setMaxHeartRate(sc.nextInt());

        System.out.print("Введите порог тревоги (пульс): ");
        config.setAlertHeartRate(sc.nextInt());
        sc.nextLine();

        System.out.println("\nВыберите цель тренировки:");
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

        System.out.println("\n=== Ваш план тренировки ===");
        System.out.println(plan);

        System.out.println("\nВыберите устройство:");
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

        System.out.println("\nНажмите Enter чтобы начать тренировку...");
        sc.nextLine();
        invoker.execute(new StartWorkoutCommand(session));

        // Симуляция 3 обновлений
        for (int i = 0; i < 3; i++) {
            System.out.println("\nНажмите Enter чтобы обновить данные устройства...");
            sc.nextLine();
            device.pullData(type);
        }

        // Лог упражнений
        System.out.print("\nВведите название упражнения: ");
        String exName = sc.nextLine();

        System.out.print("Введите кол-во повторений/минут: ");
        int reps = sc.nextInt();
        sc.nextLine();

        invoker.execute(new LogExerciseCommand(session, exName, reps));

        System.out.println("\nНажмите Enter чтобы завершить тренировку...");
        sc.nextLine();

        invoker.execute(new StopWorkoutCommand(session));

        System.out.println("\n🎉 Тренировка завершена! Спасибо за использование Fitness Tracker.");
    }
}