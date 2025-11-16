# Fitness Tracker – Final Project

This project implements a console-based **Fitness Tracker** application using 6 design patterns.  
It simulates generating workout plans, connecting to wearable devices, receiving health data in real time, and managing workout sessions.

## 🎯 Project Features

- Create custom workout plans using Builder + Strategy
- Connect to different wearable devices through a Facade
- Receive live health updates using Observer
- Start, log, and stop workouts using Command
- Store global user settings using Singleton
- Fully interactive console menu (OOP, MVC-style controller)

---

# 🧠 Design Patterns Implemented (6 Total)

## 1. **Builder**
Used to construct workout plans step-by-step.
- `WorkoutPlanBuilder`
- `SimpleWorkoutPlanBuilder`
- `WorkoutDirector`

## 2. **Strategy**
Used to switch between different workout goals.
- `FatLossStrategy`
- `MuscleGainStrategy`
- `EnduranceStrategy`

## 3. **Observer**
Used for live health monitoring (heart rate, steps).
- `WearableDevice` (Subject)
- `HeartRateConsoleObserver`
- `AlertObserver`

## 4. **Facade**
Hides the complexity of multiple device APIs.
- `DeviceApiFacade`
- `FitbitApi`, `AppleWatchApi`, `GenericBandApi`

## 5. **Command**
Encapsulates workout session actions.
- `StartWorkoutCommand`
- `LogExerciseCommand`
- `StopWorkoutCommand`
- `CommandInvoker`

## 6. **Singleton**
Stores global app configuration.
- `TrackerConfig`

---

# 👥 Team Responsibilities (Each Student = 2 Patterns)

### **Dameli Taubasar: Builder + Strategy**
Responsible for:
- WorkoutPlan creation
- Exercise models
- Strategies for workout goals

### **Begimzhan Bitore: Observer + Facade**
Responsible for:
- Connecting devices
- Pulling health data
- Notifying observers

### **Yerkegali Yergali: Command + Singleton**
Responsible for:
- Workout session logic
- Commands (start/stop/log)
- Global TrackerConfig
- Application controller

---

# ▶️ How to Run

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out fitnesstracker.FitnessTrackerApp
```

---

# 📌 Application Flow

1. User enters profile settings (Singleton)
2. User selects workout plan goal (Strategy)
3. Director builds the plan step-by-step (Builder)
4. User selects device type (Facade)
5. Device sends heart rate/steps → observers receive updates (Observer)
6. User starts workout (Command)
7. Logs exercises dynamically (Command)
8. Ends workout
9. Command history can be viewed

---

# 🗂 Project Architecture (Simplified)

```
FitnessTrackerApp
   └── FitnessTrackerController  ← menu & flow control

builder/
model/
strategy/
facade/
observer/
command/
config/
```

---