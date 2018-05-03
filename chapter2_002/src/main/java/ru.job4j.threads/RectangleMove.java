package ru.job4j.threads;

import javafx.scene.shape.Rectangle;

/**
 * Class RectangleMove.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.24
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private int limitX;
    private int step = 1;

    /**
     * Конструктор
     *
     * @param rect   фигура
     * @param limitX граница по оси X
     */
    public RectangleMove(Rectangle rect, int limitX) {
        this.rect = rect;
        this.limitX = limitX;
    }

    /**
     * Переопределенный метод Run.
     * Задаем движение фигуры на площади.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (rect.getX() == limitX || rect.getX() == 0) {
                step *= -1;
            }
            this.rect.setX(this.rect.getX() + step);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
