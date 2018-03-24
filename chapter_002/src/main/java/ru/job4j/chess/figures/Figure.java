package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
 * Figure.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.02
 */
public abstract class Figure {

    public final Cell position;

    /**
     * Конструктор фигуры
     *
     * @param position координата.
     */
    protected Figure(Cell position) {
        this.position = position;
    }

    /**
     * Строим путь движения фигуры от начальной координаты к конечной
     *
     * @param source начало
     * @param dest   конец
     * @return путь.
     */
    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    /**
     * Создает объект фигуры по координатам
     *
     * @param dest координата
     * @return новую фигуру.
     */
    public abstract Figure copy(Cell dest);

    /**
     * Строим путь движения фигуры от начальной координаты к конечной
     *
     * @param source       начало
     * @param length       длина пути
     * @param coefficientX направление движения по X
     * @param coefficientY направление движения по Y
     * @return путь.
     */
    public Cell[] way(Cell source, int length, int coefficientX, int coefficientY) {
        Cell[] result = new Cell[length];
        for (int index = 0; index < result.length; index++) {
            result[index] = new Cell(source.getX() + index * coefficientX, source.getY() + index * coefficientY);
        }
        return result;
    }

    /**
     * Валидация метода way
     *
     * @param source начало
     * @param dest   конец
     * @return путь.
     */
    public Cell[] validWay(Cell source, Cell dest) {
        Cell[] result = null;
        try {
            result = way(source, dest);
        } catch (ImpossibleMoveException e) {
            System.out.println("Фигура не может сюда пойти!");
        }
        return result;
    }

    int getCoefficient(int first, int second) {
        return Integer.compare(second, first);
    }

    public boolean isOccupaid(Cell dest) {
        return position.getX() == dest.getX() && position.getY() == dest.getY();
    }

    public boolean isCleanWay() {
        return true;
    }
}
