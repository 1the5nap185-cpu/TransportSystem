package transport;

/**
 * Запечатанный класс наземного транспорта
 */
public abstract sealed class LandTransport extends Vehicle
        permits Car, Motorcycle {

    protected final int wheelCount;
    protected final String transmissionType;

    protected LandTransport(String model, int year, Engine engine,
                            int wheelCount, String transmissionType) {
        super(model, year, engine);
        this.wheelCount = wheelCount;
        this.transmissionType = transmissionType;
    }

    @Override
    public String getInfo() {
        return "Наземный транспорт: " + model +
                " (" + year + "), " +
                wheelCount + " колес, " +
                "КПП: " + transmissionType + ", " +
                engine.toString();
    }

    public int getWheelCount() {
        return wheelCount;
    }

    public String getTransmissionType() {
        return transmissionType;
    }
}