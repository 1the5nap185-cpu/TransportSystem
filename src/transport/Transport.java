package transport;

/**
 * Базовый интерфейс для всех видов транспорта
 */
public interface Transport {
    void start();
    void stop();
    String getInfo();
    boolean isMoving();
    double calculateEfficiency();
}