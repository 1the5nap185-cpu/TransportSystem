package transport;

/**
 * Абстрактный запечатанный класс Транспортного средства
 * Разрешает наследование только определенным классам
 */
public abstract sealed class Vehicle implements Transport
        permits LandTransport, AirTransport, WaterTransport {

    protected final String model;
    protected final int year;
    protected final Engine engine;
    protected boolean isMoving;
    protected double currentSpeed;

    protected Vehicle(String model, int year, Engine engine) {
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.isMoving = false;
        this.currentSpeed = 0;
    }

    @Override
    public void start() {
        if (!engine.isRunning()) {
            engine.start();
            System.out.println(model + " готов к движению");
        }
    }

    @Override
    public void stop() {
        if (isMoving) {
            currentSpeed = 0;
            isMoving = false;
            System.out.println(model + " остановлен");
        }
        engine.stop();
    }

    @Override
    public boolean isMoving() {
        return isMoving;
    }

    public abstract void accelerate(double speed);
    public abstract void brake();

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public Engine getEngine() {
        return engine;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    @Override
    public double calculateEfficiency() {
        // Базовая формула эффективности
        return engine.getPower() / (currentSpeed > 0 ? currentSpeed : 1);
    }
}