package ru.job4j.chess.exceptions;

/**
 * ImpossibleMoveException.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.02
 */
public class ImpossibleMoveException extends Exception {

    /**
     * Ошибка - нельзя двигаться по этому пути
     *
     * @param msg сообщение
     */
    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}
