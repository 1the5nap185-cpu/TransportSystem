package transport;

/**
 * Класс автомобиля
 */
public final class Car extends LandTransport {
    private final int doorCount;
    private final String bodyType;
    private boolean lightsOn;

    public Car(String model, int year, Engine engine,
               int wheelCount, String transmissionType,
               int doorCount, String bodyType) {
        super(model, year, engine, wheelCount, transmissionType);
        this.doorCount = doorCount;
        this.bodyType = bodyType;
        this.lightsOn = false;
    }

    @Override
    public void accelerate(double speed) {
        if (engine.isRunning()) {
            currentSpeed += speed;
            isMoving = true;
            System.out.println(model + " ускоряется до " + currentSpeed + " км/ч");
        } else {
            System.out.println("Сначала запустите двигатель!");
        }
    }

    @Override
    public void brake() {
        if (currentSpeed > 0) {
            currentSpeed = Math.max(0, currentSpeed - 20);
            System.out.println(model + " замедляется до " + currentSpeed + " км/ч");
            if (currentSpeed == 0) {
                isMoving = false;
            }
        }
    }

    @Override
    public double calculateEfficiency() {
        // Для автомобиля учитываем количество дверей
        return super.calculateEfficiency() / doorCount;
    }

    public void toggleLights() {
        lightsOn = !lightsOn;
        System.out.println("Фары " + (lightsOn ? "включены" : "выключены"));
    }

    public void honk() {
        System.out.println(model + " сигналит: Би-бип!");
    }

    @Override
    public String getInfo() {
        return super.getInfo() +
                "\n  Тип кузова: " + bodyType +
                ", Количество дверей: " + doorCount +
                ", Фары: " + (lightsOn ? "вкл" : "выкл");
    }

    public int getDoorCount() {
        return doorCount;
    }

    public String getBodyType() {
        return bodyType;
    }

    public boolean isLightsOn() {
        return lightsOn;
    }
}