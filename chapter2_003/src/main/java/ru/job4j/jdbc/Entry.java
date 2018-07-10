package ru.job4j.jdbc;


/**
 * Entry
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.07.10
 */
public class Entry {

    private Integer field;

    /**
     * Возвращаем значение поля
     *
     * @return зачение field
     */
    public Integer getField() {
        return this.field;
    }

    /**
     * присваиваем новое значение для поля
     *
     * @param field значение
     */
    public void setField(Integer field) {
        this.field = field;
    }
}
