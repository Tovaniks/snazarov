package ru.job4j.chess.figures;

import ru.job4j.chess.Board;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
 * Knight. Конь.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.02
 */
public class Knight extends Figure {

    /**
     * Конструктор коня
     *
     * @param positions координата.
     */
    public Knight(Cell positions) {
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

        Cell[] result = new Cell[4];
        Cell[] subResult;
        if (!source.valid() || !dest.valid() || !(moveLikeHorse(source, dest))) {
            throw new ImpossibleMoveException("Фигура не может сюда пойти!");
        } else {
            if (Math.abs(source.getX() - dest.getX()) == 3) {
                subResult = way(source, 3, getCoefficient(source.getX(), dest.getX()), 0);
            } else {
                subResult = way(source, 3, 0, getCoefficient(source.getY(), dest.getY()));
            }
        }
        System.arraycopy(subResult, 0, result, 0, 3);
        result[3] = dest;
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
        return new Knight(dest);
    }

    /**
     * Проверяем, является ли движение фигуры от начала до конца движением, как у коня
     *
     * @param source начало
     * @param dest   конец
     * @return true/false.
     */
    static boolean moveLikeHorse(Cell source, Cell dest) {
        return Math.abs(source.getX() - dest.getX()) == 1 && Math.abs(source.getY() - dest.getY()) == 2
                || Math.abs(source.getX() - dest.getX()) == 2 && Math.abs(source.getY() - dest.getY()) == 1;
    }
}
