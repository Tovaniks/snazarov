package ru.job4j.test.action;


/**
 * Class Bid
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.23
 */
public class Bid implements Action {

    /**
     * Покупка
     *
     * @return Bid
     */
    @Override
    public String action() {
        return "Bid";
    }    
}
