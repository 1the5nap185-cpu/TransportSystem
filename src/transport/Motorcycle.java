package transport;

/**
 * Класс мотоцикла
 */
public final class Motorcycle extends LandTransport {
    private final boolean hasSidecar;
    private final String motorcycleType;
    private boolean isKickstandDown;

    public Motorcycle(String model, int year, Engine engine,
                      String transmissionType, boolean hasSidecar,
                      String motorcycleType) {
        super(model, year, engine, 2, transmissionType);
        this.hasSidecar = hasSidecar;
        this.motorcycleType = motorcycleType;
        this.isKickstandDown = true;
    }

    @Override
    public void accelerate(double speed) {
        if (engine.isRunning() && !isKickstandDown) {
            currentSpeed += speed;
            isMoving = true;
            System.out.println(model + " ускоряется до " + currentSpeed + " км/ч");
        } else if (isKickstandDown) {
            System.out.println("Поднимите подножку!");
        } else {
            System.out.println("Сначала запустите двигатель!");
        }
    }

    @Override
    public void brake() {
        if (currentSpeed > 0) {
            currentSpeed = Math.max(0, currentSpeed - 30);
            System.out.println(model + " замедляется до " + currentSpeed + " км/ч");
            if (currentSpeed == 0) {
                isMoving = false;
                isKickstandDown = true;
            }
        }
    }

    public void raiseKickstand() {
        if (!isMoving) {
            isKickstandDown = false;
            System.out.println("Подножка поднята");
        }
    }

    public void lowerKickstand() {
        if (!isMoving) {
            isKickstandDown = true;
            System.out.println("Подножка опущена");
        }
    }

    public void wheelie() {
        if (currentSpeed > 30) {
            System.out.println(model + " делает вилли!");
        } else {
            System.out.println("Слишком медленно для вилли");
        }
    }

    @Override
    public String getInfo() {
        return super.getInfo() +
                "\n  Тип мотоцикла: " + motorcycleType +
                ", Коляска: " + (hasSidecar ? "есть" : "нет") +
                ", Подножка: " + (isKickstandDown ? "опущена" : "поднята");
    }

    public boolean hasSidecar() {
        return hasSidecar;
    }

    public String getMotorcycleType() {
        return motorcycleType;
    }

    public boolean isKickstandDown() {
        return isKickstandDown;
    }
}