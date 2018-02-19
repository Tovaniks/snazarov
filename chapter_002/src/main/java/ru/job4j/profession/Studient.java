package ru.job4j.profession;


/**
 * Studient.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.19
 */


public class Studient {

    public String name;

    /**
     * Присваиваем имя студенту
     *
     * @param name Имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращаем имя студента
     *
     * @return имя.
     */
    public String getName() {
        return this.name;
    }
}
