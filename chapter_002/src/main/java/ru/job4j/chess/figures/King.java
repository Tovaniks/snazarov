package ru.job4j.chess.figures;

import ru.job4j.chess.Board;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
 * King. Король
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.02
 */
public class King extends Figure {

    protected int length = 1;

    /**
     * Конструктор короля
     *
     * @param positions координата.
     */
    public King(Cell positions) {
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
        if (!Board.validCells(source, dest) || !(Rook.moveLikeRook(source, dest) || Bishop.moveLikeBishop(source, dest))
                || Math.abs(source.getX() - dest.getX()) > length || Math.abs(source.getY() - dest.getY()) > length) {
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
        return new King(dest);
    }
}
