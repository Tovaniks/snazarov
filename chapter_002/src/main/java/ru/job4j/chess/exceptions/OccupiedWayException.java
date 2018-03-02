package ru.job4j.chess.exceptions;

/**
 * OccupiedWayException.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.02
 */
public class OccupiedWayException extends Exception {

    /**
     * Ошибка - на пути есть фигура
     *
     * @param msg сообщение
     */
    public OccupiedWayException(String msg) {
        super(msg);
    }
}
