package ru.job4j.coffee;

/**
 * CoffeeMachine.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.19
 */
public class PriceNotValidException extends Throwable {

    /**
     * Ошибка - цена/сумма не корректна
     *
     * @param msg сообщение
     */
    public PriceNotValidException(String msg) {
        super(msg);
    }
}
