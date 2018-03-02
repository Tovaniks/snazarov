package ru.job4j.chess.figures;

import ru.job4j.chess.Board;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
 * Bishop. Слон.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.02
 */
public class Bishop extends Figure {

    /**
     * Конструктор слона
     *
     * @param positions координата.
     */
    public Bishop(Cell positions) {
        super(positions);
    }

    /**
     * Строим путь движения фигуры от начальной координаты к конечной
     *
     * @param source начало
     * @param dest   конец
     * @return путь.
     */
    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        int coefficientX = getCoefficient(source.getX(), dest.getX());
        int coefficientY = getCoefficient(source.getY(), dest.getY());
        Cell[] result;
        if (Board.validCells(source, dest) && moveLikeBishop(source, dest)) {
            result = way(source, Math.abs(dest.getX() - source.getX()) + 1, coefficientX, coefficientY);
        } else {
            throw new ImpossibleMoveException("Фигура не может сюда пойти!");
        }
        return result;
    }

    /**
     * Создает объект фигуры по координатам
     *
     * @param dest координата
     * @return новую фигуру.
     */
    @Override
    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }

    /**
     * Проверяем, является ли движение фигуры от начала до конца движением, как у ладьи
     *
     * @param source начало
     * @param dest   конец
     * @return true/false.
     */
    static boolean moveLikeBishop(Cell source, Cell dest) {
        return (Math.abs(dest.getX() - source.getX()) == Math.abs(dest.getY() - source.getY()));
    }
}
