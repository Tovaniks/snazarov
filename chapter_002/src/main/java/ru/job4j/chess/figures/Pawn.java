package ru.job4j.chess.figures;

import ru.job4j.chess.Board;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
 * Pawn. Пешка
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.02
 */
public class Pawn extends Figure {

    private static final int MAXLENGTH = 1;

    /**
     * Конструктор пешки
     *
     * @param positions координата.
     */
    public Pawn(Cell positions) {
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
        if (!new Board().validCells(source, dest) || source.getX() != dest.getX() || Math.abs(source.getY() - dest.getY()) > MAXLENGTH) {
            throw new ImpossibleMoveException("Фигура не может сюда пойти!");
        }
        return new Cell[]{source, dest};
    }

    /**
     * Создает объект фигуры по координатам
     *
     * @param dest координата
     * @return новую фигуру.
     */
    @Override
    public Figure copy(Cell dest) {
        return new Pawn(dest);
    }
}
