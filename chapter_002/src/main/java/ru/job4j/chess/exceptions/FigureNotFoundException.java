package ru.job4j.chess.exceptions;

/**
 * FigureNotFoundException.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.02
 */
public class FigureNotFoundException extends Exception {

    /**
     * Ошибка - фигура не найдена
     *
     * @param msg сообщение
     */
    public FigureNotFoundException(String msg) {
        super(msg);
    }
}
