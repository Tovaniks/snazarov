package ru.job4j.generic;

/**
 * Class Base.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.09
 */
public abstract class Base {

    private final String id;

    /**
     * Конструктор
     *
     * @param id ID записи
     */
    protected Base(final String id) {
        this.id = id;
    }


    /**
     * Получаем ID записи
     *
     * @return ID
     */
    public String getId() {
        return id;
    }

}
