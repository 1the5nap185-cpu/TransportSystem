import ui.TransportUI;

/**
 * Главный класс приложения
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Программа управления транспортом ===");
        System.out.println("Демонстрация иерархии классов с использованием:");
        System.out.println("- Запечатанных классов (sealed)");
        System.out.println("- Перечислений (enum)");
        System.out.println("- Абстрактных классов и интерфейсов");
        System.out.println("- Наследования и полиморфизма\n");

        TransportUI ui = new TransportUI();
        ui.start();
    }
}