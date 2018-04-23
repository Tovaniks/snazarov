package ru.job4j.test.action;

/**
 * Class Ask
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.23
 */
public class Ask implements Action {

    /**
     * Продажа
     *
     * @return ask
     */
    @Override
    public String action() {
        return "Ask";
    }
    
}
