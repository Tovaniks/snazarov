
package ru.job4j.tracker;

/**
 * BaseAction
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.25
 */
public abstract class BaseAction implements UserAction {
    private final int key;
    private final String name;

    /**
     * конструктор
     *
     * @param key  id пункта меню
     * @param name название пункта меню
     */
    protected BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Возвращаем ID пункта меню
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /**
     * Возвращаем описание пункта меню
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}
