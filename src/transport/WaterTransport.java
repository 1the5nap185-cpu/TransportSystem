package transport;

/**
 * Запечатанный класс водного транспорта
 */
public abstract sealed class WaterTransport extends Vehicle
        permits Ship {

    protected final double displacement;
    protected final String hullMaterial;

    protected WaterTransport(String model, int year, Engine engine,
                             double displacement, String hullMaterial) {
        super(model, year, engine);
        this.displacement = displacement;
        this.hullMaterial = hullMaterial;
    }

    @Override
    public String getInfo() {
        return "Водный транспорт: " + model +
                " (" + year + "), " +
                "Водоизмещение: " + displacement + "т, " +
                "Материал корпуса: " + hullMaterial + ", " +
                engine.toString();
    }

    public abstract void anchor();
    public abstract void raiseAnchor();

    public double getDisplacement() {
        return displacement;
    }

    public String getHullMaterial() {
        return hullMaterial;
    }
}