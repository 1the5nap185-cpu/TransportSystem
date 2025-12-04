package transport;

/**
 * Запечатанный класс воздушного транспорта
 */
public abstract sealed class AirTransport extends Vehicle
        permits Airplane {

    protected final double wingspan;
    protected final int maxAltitude;

    protected AirTransport(String model, int year, Engine engine,
                           double wingspan, int maxAltitude) {
        super(model, year, engine);
        this.wingspan = wingspan;
        this.maxAltitude = maxAltitude;
    }

    @Override
    public String getInfo() {
        return "Воздушный транспорт: " + model +
                " (" + year + "), " +
                "Размах крыльев: " + wingspan + "м, " +
                "Макс. высота: " + maxAltitude + "м, " +
                engine.toString();
    }

    public abstract void takeOff();
    public abstract void land();

    public double getWingspan() {
        return wingspan;
    }

    public int getMaxAltitude() {
        return maxAltitude;
    }
}