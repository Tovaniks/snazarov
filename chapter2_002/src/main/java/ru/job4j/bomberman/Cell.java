package ru.job4j.bomberman;

/**
 * Class Cell.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.06.22
 */
public class Cell {

    private final int x;
    private final int y;

    /**
     * Конструктор координаты
     *
     * @param x - x
     * @param y - y
     */
    public Cell(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращаем значение x
     *
     * @return x
     */
    public int getX() {
        return this.x;
    }


    /**
     * Возвращаем значение y
     *
     * @return y
     */
    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object obj) {
        boolean successful = false;
        if (obj instanceof Cell) {
            Cell point = (Cell) obj;
            successful = (x == point.getX()) && (y == point.getY());
        }
        return successful;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.x;
        result = 31 * result + this.y;
        return result;
    }
}
