package ui;

import transport.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Пользовательский интерфейс для управления транспортом
 */
public class TransportUI {
    private final List<Vehicle> vehicles;
    private final Scanner scanner;

    public TransportUI() {
        vehicles = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("🚗🚁🚢 СИСТЕМА УПРАВЛЕНИЯ ТРАНСПОРТОМ 🚗🚁🚢");
        System.out.println("===========================================");

        // Создаем несколько транспортных средств по умолчанию
        createDefaultVehicles();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Выберите действие: ");

            switch (choice) {
                case 1 -> displayAllVehicles();
                case 2 -> createNewVehicle();
                case 3 -> controlVehicle();
                case 4 -> displayTransportInfo();
                case 5 -> testAllVehicles();
                case 6 -> calculateEfficiency();
                case 0 -> {
                    running = false;
                    System.out.println("Выход из программы...");
                }
                default -> System.out.println("Неверный выбор!");
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
        System.out.println("1. Показать все транспортные средства");
        System.out.println("2. Создать новое транспортное средство");
        System.out.println("3. Управлять транспортным средством");
        System.out.println("4. Показать информацию о транспорте");
        System.out.println("5. Протестировать все транспортные средства");
        System.out.println("6. Рассчитать эффективность");
        System.out.println("0. Выход");
    }

    private void createDefaultVehicles() {
        // Создаем автомобиль
        Engine carEngine = new Engine("V6 Turbo", 300, FuelType.PETROL);
        Car car = new Car("Toyota Camry", 2022, carEngine, 4, "Автомат", 4, "Седан");

        // Создаем мотоцикл
        Engine bikeEngine = new Engine("Inline-4", 120, FuelType.PETROL);
        Motorcycle bike = new Motorcycle("Honda CBR", 2021, bikeEngine, "Механика", false, "Спорт");

        // Создаем самолет
        Engine planeEngine = new Engine("Turbofan", 50000, FuelType.KEROSENE);
        Airplane plane = new Airplane("Boeing 737", 2020, planeEngine, 35.8, 12500, 189, 20.5);

        // Создаем корабль
        Engine shipEngine = new Engine("Diesel Marine", 10000, FuelType.DIESEL);
        Ship ship = new Ship("Queen Mary 2", 2018, shipEngine, 150000, "Сталь", 12, "Круизный лайнер");

        vehicles.add(car);
        vehicles.add(bike);
        vehicles.add(plane);
        vehicles.add(ship);

        System.out.println("Создано 4 транспортных средства по умолчанию");
    }

    private void displayAllVehicles() {
        System.out.println("\n=== ВСЕ ТРАНСПОРТНЫЕ СРЕДСТВА ===");
        if (vehicles.isEmpty()) {
            System.out.println("Нет транспортных средств");
            return;
        }

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            String type = getVehicleType(vehicle);
            System.out.printf("%d. %s: %s (%d)%n",
                    i + 1, type, vehicle.getModel(), vehicle.getYear());
        }
    }

    private String getVehicleType(Vehicle vehicle) {
        if (vehicle instanceof Car) return "Автомобиль";
        if (vehicle instanceof Motorcycle) return "Мотоцикл";
        if (vehicle instanceof Airplane) return "Самолет";
        if (vehicle instanceof Ship) return "Корабль";
        return "Транспорт";
    }

    private void createNewVehicle() {
        System.out.println("\n=== СОЗДАНИЕ НОВОГО ТРАНСПОРТА ===");
        System.out.println("Выберите тип транспорта:");
        System.out.println("1. Автомобиль");
        System.out.println("2. Мотоцикл");
        System.out.println("3. Самолет");
        System.out.println("4. Корабль");

        int type = getIntInput("Ваш выбор: ");

        switch (type) {
            case 1 -> createCar();
            case 2 -> createMotorcycle();
            case 3 -> createAirplane();
            case 4 -> createShip();
            default -> System.out.println("Неверный выбор");
        }
    }

    private void createCar() {
        System.out.println("\n--- Создание автомобиля ---");
        String model = getStringInput("Модель: ");
        int year = getIntInput("Год выпуска: ");

        System.out.println("Двигатель:");
        String engineModel = getStringInput("Модель двигателя: ");
        double power = getDoubleInput("Мощность (л.с.): ");
        System.out.println("Тип топлива: 1-Бензин, 2-Дизель, 3-Электричество");
        int fuelChoice = getIntInput("Выбор: ");
        FuelType fuelType = switch (fuelChoice) {
            case 1 -> FuelType.PETROL;
            case 2 -> FuelType.DIESEL;
            case 3 -> FuelType.ELECTRIC;
            default -> FuelType.PETROL;
        };

        Engine engine = new Engine(engineModel, power, fuelType);
        int wheels = getIntInput("Количество колес: ");
        String transmission = getStringInput("Тип КПП: ");
        int doors = getIntInput("Количество дверей: ");
        String bodyType = getStringInput("Тип кузова: ");

        Car car = new Car(model, year, engine, wheels, transmission, doors, bodyType);
        vehicles.add(car);
        System.out.println("Автомобиль создан успешно!");
    }

    private void createMotorcycle() {
        System.out.println("\n--- Создание мотоцикла ---");
        String model = getStringInput("Модель: ");
        int year = getIntInput("Год выпуска: ");

        System.out.println("Двигатель:");
        String engineModel = getStringInput("Модель двигателя: ");
        double power = getDoubleInput("Мощность (л.с.): ");
        Engine engine = new Engine(engineModel, power, FuelType.PETROL);

        String transmission = getStringInput("Тип КПП: ");
        boolean hasSidecar = getYesNoInput("Есть коляска? (y/n): ");
        String bikeType = getStringInput("Тип мотоцикла: ");

        Motorcycle bike = new Motorcycle(model, year, engine, transmission, hasSidecar, bikeType);
        vehicles.add(bike);
        System.out.println("Мотоцикл создан успешно!");
    }

    private void createAirplane() {
        System.out.println("\n--- Создание самолета ---");
        String model = getStringInput("Модель: ");
        int year = getIntInput("Год выпуска: ");

        System.out.println("Двигатель:");
        String engineModel = getStringInput("Модель двигателя: ");
        double power = getDoubleInput("Мощность (л.с.): ");
        Engine engine = new Engine(engineModel, power, FuelType.KEROSENE);

        double wingspan = getDoubleInput("Размах крыльев (м): ");
        int maxAlt = getIntInput("Максимальная высота (м): ");
        int passengers = getIntInput("Вместимость пассажиров: ");
        double cargo = getDoubleInput("Грузоподъемность (т): ");

        Airplane plane = new Airplane(model, year, engine, wingspan, maxAlt, passengers, cargo);
        vehicles.add(plane);
        System.out.println("Самолет создан успешно!");
    }

    private void createShip() {
        System.out.println("\n--- Создание корабля ---");
        String model = getStringInput("Модель: ");
        int year = getIntInput("Год выпуска: ");

        System.out.println("Двигатель:");
        String engineModel = getStringInput("Модель двигателя: ");
        double power = getDoubleInput("Мощность (л.с.): ");
        FuelType fuelType = getIntInput("Тип топлива (1-Дизель, 2-Ядерное): ") == 1
                ? FuelType.DIESEL : FuelType.NUCLEAR;
        Engine engine = new Engine(engineModel, power, fuelType);

        double displacement = getDoubleInput("Водоизмещение (т): ");
        String hull = getStringInput("Материал корпуса: ");
        int decks = getIntInput("Количество палуб: ");
        String shipType = getStringInput("Тип судна: ");

        Ship ship = new Ship(model, year, engine, displacement, hull, decks, shipType);
        vehicles.add(ship);
        System.out.println("Корабль создан успешно!");
    }

    private void controlVehicle() {
        if (vehicles.isEmpty()) {
            System.out.println("Нет транспортных средств для управления");
            return;
        }

        displayAllVehicles();
        int index = getIntInput("Выберите транспортное средство (номер): ") - 1;

        if (index < 0 || index >= vehicles.size()) {
            System.out.println("Неверный номер");
            return;
        }

        Vehicle vehicle = vehicles.get(index);
        System.out.println("\nУправление: " + vehicle.getModel());
        System.out.println("1. Запустить двигатель");
        System.out.println("2. Остановить двигатель");
        System.out.println("3. Ускориться");
        System.out.println("4. Затормозить");

        // Специфичные действия для каждого типа
        if (vehicle instanceof Car car) {
            System.out.println("5. Включить/выключить фары");
            System.out.println("6. Подать сигнал");
        } else if (vehicle instanceof Motorcycle bike) {
            System.out.println("5. Поднять подножку");
            System.out.println("6. Опустить подножку");
            System.out.println("7. Сделать вилли");
        } else if (vehicle instanceof Airplane plane) {
            System.out.println("5. Взлететь");
            System.out.println("6. Приземлиться");
            System.out.println("7. Изменить высоту");
        } else if (vehicle instanceof Ship ship) {
            System.out.println("5. Опустить якорь");
            System.out.println("6. Поднять якорь");
            System.out.println("7. Издать гудок");
            System.out.println("8. Изменить глубину");
        }

        System.out.println("0. Назад");

        int action = getIntInput("Выберите действие: ");

        switch (action) {
            case 1 -> vehicle.start();
            case 2 -> vehicle.stop();
            case 3 -> {
                double speed = getDoubleInput("На сколько ускориться: ");
                vehicle.accelerate(speed);
            }
            case 4 -> vehicle.brake();
            case 5 -> handleSpecialAction(vehicle, 5);
            case 6 -> handleSpecialAction(vehicle, 6);
            case 7 -> handleSpecialAction(vehicle, 7);
            case 8 -> handleSpecialAction(vehicle, 8);
            case 0 -> System.out.println("Возврат в меню");
            default -> System.out.println("Неверное действие");
        }
    }

    private void handleSpecialAction(Vehicle vehicle, int action) {
        if (vehicle instanceof Car car) {
            if (action == 5) car.toggleLights();
            else if (action == 6) car.honk();
        } else if (vehicle instanceof Motorcycle bike) {
            if (action == 5) bike.raiseKickstand();
            else if (action == 6) bike.lowerKickstand();
            else if (action == 7) bike.wheelie();
        } else if (vehicle instanceof Airplane plane) {
            if (action == 5) plane.takeOff();
            else if (action == 6) plane.land();
            else if (action == 7) {
                int alt = getIntInput("Новая высота (м): ");
                plane.changeAltitude(alt);
            }
        } else if (vehicle instanceof Ship ship) {
            if (action == 5) ship.anchor();
            else if (action == 6) ship.raiseAnchor();
            else if (action == 7) ship.soundHorn();
            else if (action == 8) {
                double depth = getDoubleInput("Новая глубина (м): ");
                ship.changeDepth(depth);
            }
        }
    }

    private void displayTransportInfo() {
        if (vehicles.isEmpty()) {
            System.out.println("Нет транспортных средств");
            return;
        }

        displayAllVehicles();
        int index = getIntInput("Выберите транспортное средство (номер): ") - 1;

        if (index < 0 || index >= vehicles.size()) {
            System.out.println("Неверный номер");
            return;
        }

        Vehicle vehicle = vehicles.get(index);
        System.out.println("\n=== ИНФОРМАЦИЯ О ТРАНСПОРТЕ ===");
        System.out.println(vehicle.getInfo());
        System.out.println("Текущая скорость: " + vehicle.getCurrentSpeed());
        System.out.println("В движении: " + (vehicle.isMoving() ? "Да" : "Нет"));
        System.out.printf("Эффективность: %.2f%n", vehicle.calculateEfficiency());
    }

    private void testAllVehicles() {
        System.out.println("\n=== ТЕСТИРОВАНИЕ ВСЕХ ТРАНСПОРТНЫХ СРЕДСТВ ===");

        for (Vehicle vehicle : vehicles) {
            System.out.println("\n--- Тестирование: " + vehicle.getModel() + " ---");

            // Запускаем двигатель
            vehicle.start();

            // Ускоряемся
            vehicle.accelerate(50);

            // Показываем информацию
            System.out.println(vehicle.getInfo());
            System.out.printf("Эффективность: %.2f%n", vehicle.calculateEfficiency());

            // Тормозим
            vehicle.brake();

            // Останавливаем двигатель
            vehicle.stop();

            System.out.println("--- Тест завершен ---");
        }

        System.out.println("\nВсе транспортные средства протестированы!");
    }

    private void calculateEfficiency() {
        System.out.println("\n=== РАСЧЕТ ЭФФЕКТИВНОСТИ ===");

        if (vehicles.isEmpty()) {
            System.out.println("Нет транспортных средств");
            return;
        }

        double totalEfficiency = 0;
        Vehicle mostEfficient = vehicles.get(0);

        for (Vehicle vehicle : vehicles) {
            double efficiency = vehicle.calculateEfficiency();
            totalEfficiency += efficiency;

            System.out.printf("%s: %.2f%n", vehicle.getModel(), efficiency);

            if (efficiency > mostEfficient.calculateEfficiency()) {
                mostEfficient = vehicle;
            }
        }

        double average = totalEfficiency / vehicles.size();
        System.out.printf("\nСредняя эффективность: %.2f%n", average);
        System.out.println("Самый эффективный транспорт: " + mostEfficient.getModel());
    }

    // Вспомогательные методы для ввода
    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Пожалуйста, введите число!");
            scanner.next();
            System.out.print(prompt);
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        return value;
    }

    private double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Пожалуйста, введите число!");
            scanner.next();
            System.out.print(prompt);
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // Очистка буфера
        return value;
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private boolean getYesNoInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().toLowerCase();
        return input.equals("y") || input.equals("yes") || input.equals("да");
    }
}