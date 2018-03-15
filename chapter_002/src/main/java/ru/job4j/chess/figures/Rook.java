package ru.job4j.chess.figures;

import ru.job4j.chess.Board;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
 * Rook. Ладья.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.02
 */
public class Rook extends Figure {

    /**
     * Конструктор ладьи
     *
     * @param positions координата.
     */
    public Rook(Cell positions) {
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
        if (!source.valid() || !dest.valid() || !moveLikeRook(source, dest)) {
            throw new ImpossibleMoveException("Фигура не может сюда пойти!");
        } else {
            result = way(source, Math.max(Math.abs(dest.getX() - source.getX()), Math.abs(dest.getY() - source.getY())) + 1, coefficientX, coefficientY);
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
        return new Rook(dest);
    }

    /**
     * Проверяем, является ли движение фигуры от начала до конца движением, как у ладьи
     *
     * @param source начало
     * @param dest   конец
     * @return true/false.
     */
    static boolean moveLikeRook(Cell source, Cell dest) {
        return dest.getX() == source.getX() || dest.getY() == source.getY();
    }
}
