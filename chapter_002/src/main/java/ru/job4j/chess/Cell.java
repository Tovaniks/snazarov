package ru.job4j.chess;

/**
 * Cell.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.02
 */
public class Cell {

    private final int x;
    private final int y;

    /**
     * Конструктор клетки
     *
     * @param x x
     * @param y y
     */
    public Cell(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращаем координату x
     *
     * @return x.
     */
    public int getX() {
        return x;
    }

    /**
     * Возвращаем координату y
     *
     * @return y.
     */
    public int getY() {
        return y;
    }
}
