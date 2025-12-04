package transport;

/**
 * Класс самолета
 */
public final class Airplane extends AirTransport {
    private final int passengerCapacity;
    private final double cargoCapacity;
    private int currentAltitude;
    private boolean isInAir;

    public Airplane(String model, int year, Engine engine,
                    double wingspan, int maxAltitude,
                    int passengerCapacity, double cargoCapacity) {
        super(model, year, engine, wingspan, maxAltitude);
        this.passengerCapacity = passengerCapacity;
        this.cargoCapacity = cargoCapacity;
        this.currentAltitude = 0;
        this.isInAir = false;
    }

    @Override
    public void accelerate(double speed) {
        if (engine.isRunning()) {
            currentSpeed += speed;
            System.out.println(model + " ускоряется до " + currentSpeed + " км/ч");
            if (currentSpeed > 200 && !isInAir) {
                takeOff();
            }
        } else {
            System.out.println("Сначала запустите двигатель!");
        }
    }

    @Override
    public void brake() {
        if (currentSpeed > 0 && !isInAir) {
            currentSpeed = Math.max(0, currentSpeed - 30);
            System.out.println(model + " замедляется до " + currentSpeed + " км/ч");
        } else if (isInAir) {
            System.out.println("Нельзя тормозить в воздухе!");
        }
    }

    @Override
    public void takeOff() {
        if (currentSpeed > 200 && !isInAir) {
            isInAir = true;
            isMoving = true;
            currentAltitude = 1000;
            System.out.println(model + " взлетел! Высота: " + currentAltitude + "м");
        } else {
            System.out.println("Недостаточно скорости для взлета");
        }
    }

    @Override
    public void land() {
        if (isInAir) {
            currentSpeed = 150;
            currentAltitude = 0;
            isInAir = false;
            isMoving = false;
            System.out.println(model + " успешно приземлился");
        }
    }

    public void changeAltitude(int altitude) {
        if (isInAir) {
            if (altitude <= maxAltitude) {
                currentAltitude = altitude;
                System.out.println("Высота изменена: " + currentAltitude + "м");
            } else {
                System.out.println("Превышена максимальная высота!");
            }
        } else {
            System.out.println("Самолет не в воздухе");
        }
    }

    @Override
    public String getInfo() {
        return super.getInfo() +
                "\n  Вместимость: " + passengerCapacity + " пассажиров" +
                ", Грузоподъемность: " + cargoCapacity + "т" +
                ", В воздухе: " + (isInAir ? "да" : "нет") +
                ", Текущая высота: " + currentAltitude + "м";
    }

    @Override
    public double calculateEfficiency() {
        // Для самолета учитываем высоту
        return (engine.getPower() * (isInAir ? 2 : 1)) / currentSpeed;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public int getCurrentAltitude() {
        return currentAltitude;
    }

    public boolean isInAir() {
        return isInAir;
    }
}