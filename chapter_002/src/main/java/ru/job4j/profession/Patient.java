package ru.job4j.profession;


/**
 * Patient.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.19
 */


public class Patient {


    public String name;

    /**
     * Возвращаем имя пациента
     *
     * @return имя пациента.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Присваиваем имя пациенту
     *
     * @param name Имя
     */
    public void setName(String name) {
        this.name = name;
    }
}
