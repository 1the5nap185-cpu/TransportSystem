package transport;

/**
 * Класс двигателя
 */
public class Engine {
    private final String model;
    private final double power; // в лошадиных силах
    private final FuelType fuelType;
    private boolean isRunning;

    public Engine(String model, double power, FuelType fuelType) {
        this.model = model;
        this.power = power;
        this.fuelType = fuelType;
        this.isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Двигатель " + model + " запущен");
        } else {
            System.out.println("Двигатель уже работает");
        }
    }

    public void stop() {
        if (isRunning) {
            isRunning = false;
            System.out.println("Двигатель " + model + " остановлен");
        } else {
            System.out.println("Двигатель уже остановлен");
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getModel() {
        return model;
    }

    public double getPower() {
        return power;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    @Override
    public String toString() {
        return "Двигатель: " + model +
                ", Мощность: " + power + " л.с." +
                ", Топливо: " + fuelType.getDescription() +
                ", Состояние: " + (isRunning ? "работает" : "остановлен");
    }
}