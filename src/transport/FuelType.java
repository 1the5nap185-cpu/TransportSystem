package transport;

/**
 * Перечисление типов топлива
 */
public enum FuelType {
    PETROL("Бензин"),
    DIESEL("Дизель"),
    ELECTRIC("Электричество"),
    KEROSENE("Керосин"),
    NUCLEAR("Ядерное топливо"),
    HUMAN_POWER("Мышечная сила");

    private final String description;

    FuelType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}