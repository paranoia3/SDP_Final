# Fitness Tracker — Java Project (Design Patterns)

This project is a console-based **Fitness Tracker** system implemented in Java.  
It demonstrates the following six design patterns:

- **Builder**
- **Strategy**
- **Abstract Factory**
- **Adapter**
- **Facade**
- **Observer**

## Team Members & Responsibilities

### 1. Dameli Taubasar — Builder + Strategy
- Workout plan architecture
- WorkoutPlanBuilder
- WorkoutDirector
- IntensityStrategy implementations
- Exercise + WorkoutPlan models

### 2. Begimzhan Bitore — Observer + Facade
- WearableDevice (Subject)
- HealthData & Observers
- DeviceFacade subsystem

### 3. Yerkegali Yergali — Abstract Factory + Adapter
- VendorApi interfaces
- DeviceAdapter implementations
- Device factories (Fitbit/AppleWatch/GenericBand)

---

# Project Structure

```
src/
 ├─ fitnesstracker/
 │   ├─ FitnessTrackerApp.java
 │   ├─ FitnessTrackerController.java
 │   └─ UserConfig.java
 │
 ├─ model/
 │   ├─ Exercise.java
 │   ├─ ExerciseType.java
 │   └─ WorkoutPlan.java
 │
 ├─ builder/
 │   ├─ WorkoutPlanBuilder.java
 │   ├─ SimpleWorkoutPlanBuilder.java
 │   └─ WorkoutDirector.java
 │
 ├─ strategy/
 │   ├─ IntensityStrategy.java
 │   ├─ FatLossStrategy.java
 │   ├─ MuscleGainStrategy.java
 │   └─ EnduranceStrategy.java
 │
 ├─ observer/
 │   ├─ HealthData.java
 │   ├─ HealthDataObserver.java
 │   ├─ WearableDevice.java
 │   └─ AlertObserver.java
 │
 ├─ device/
 │   ├─ VendorApi.java
 │   ├─ FitbitApi.java
 │   ├─ AppleWatchApi.java
 │   ├─ GenericBandApi.java
 │   ├─ DeviceAdapter.java
 │   ├─ FitbitAdapter.java
 │   ├─ AppleWatchAdapter.java
 │   ├─ GenericBandAdapter.java
 │   ├─ AbstractDeviceFactory.java
 │   ├─ FitbitFactory.java
 │   ├─ AppleWatchFactory.java
 │   ├─ GenericBandFactory.java
 │   └─ DeviceFacade.java
```

---

# Pattern Overview

## 1. Builder Pattern
Used to construct a multi-step **WorkoutPlan** object.

## 2. Strategy Pattern
Used to generate different workout plans depending on the user’s goal:
- Fat Loss
- Muscle Gain
- Endurance

## 3. Abstract Factory Pattern
Creates device families (VendorApi + Adapter + WearableDevice).

## 4. Adapter Pattern
Unifies the raw vendor API calls into a simple interface:
```
getHeartRate()
getSteps()
```

## 5. Facade Pattern
Provides a simplified API for device handling:
- connect
- pullAndNotify

## 6. Observer Pattern
Used for real-time heart rate & steps updates:
- Subject → WearableDevice
- Observers → Console observer + Alert observer

---

# Running the Project

```
javac fitnesstracker/FitnessTrackerApp.java
java fitnesstracker.FitnessTrackerApp
```

---

# Future Improvements

- Add GUI (JavaFX)
- Add database storage
- Add more strategies and devices
- Add analytics using Visitor/Decorator
