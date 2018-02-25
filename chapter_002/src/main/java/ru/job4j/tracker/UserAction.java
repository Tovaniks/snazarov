package ru.job4j.tracker;

/**
 * UserAction
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.21
 */

public interface UserAction {

    /**
     * Возвращаем ID пункта меню
     */

    int getKey();

    /**
     * Выполняем действие пункта меню
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    void execute(Input input, Tracker tracker);

    /**
     * Возвращаем описание пункта меню
     */
    String info();
}
