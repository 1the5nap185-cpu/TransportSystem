package transport;

/**
 * Класс корабля
 */
public final class Ship extends WaterTransport {
    private final int deckCount;
    private final String shipType;
    private boolean anchorDropped;
    private double currentDepth;

    public Ship(String model, int year, Engine engine,
                double displacement, String hullMaterial,
                int deckCount, String shipType) {
        super(model, year, engine, displacement, hullMaterial);
        this.deckCount = deckCount;
        this.shipType = shipType;
        this.anchorDropped = false;
        this.currentDepth = 10; // начальная глубина
    }

    @Override
    public void accelerate(double speed) {
        if (engine.isRunning() && !anchorDropped) {
            currentSpeed += speed / 2; // Корабли ускоряются медленнее
            isMoving = true;
            System.out.println(model + " ускоряется до " + currentSpeed + " узлов");
        } else if (anchorDropped) {
            System.out.println("Поднимите якорь!");
        } else {
            System.out.println("Сначала запустите двигатель!");
        }
    }

    @Override
    public void brake() {
        if (currentSpeed > 0) {
            currentSpeed = Math.max(0, currentSpeed - 5);
            System.out.println(model + " замедляется до " + currentSpeed + " узлов");
            if (currentSpeed == 0) {
                isMoving = false;
            }
        }
    }

    @Override
    public void anchor() {
        if (!isMoving) {
            anchorDropped = true;
            System.out.println("Якорь опущен");
        } else {
            System.out.println("Нельзя бросать якорь на ходу!");
        }
    }

    @Override
    public void raiseAnchor() {
        anchorDropped = false;
        System.out.println("Якорь поднят");
    }

    public void soundHorn() {
        System.out.println(model + " гудит: Ту-у-у!");
    }

    public void changeDepth(double depth) {
        currentDepth = depth;
        System.out.println("Глубина изменена: " + currentDepth + "м");
    }

    @Override
    public String getInfo() {
        return super.getInfo() +
                "\n  Тип судна: " + shipType +
                ", Количество палуб: " + deckCount +
                ", Якорь: " + (anchorDropped ? "опущен" : "поднят") +
                ", Текущая глубина: " + currentDepth + "м";
    }

    @Override
    public double calculateEfficiency() {
        // Для корабля учитываем водоизмещение
        return engine.getPower() / (displacement * currentSpeed);
    }

    public int getDeckCount() {
        return deckCount;
    }

    public String getShipType() {
        return shipType;
    }

    public boolean isAnchorDropped() {
        return anchorDropped;
    }

    public double getCurrentDepth() {
        return currentDepth;
    }
}