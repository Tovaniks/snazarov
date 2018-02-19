package ru.job4j.profession;


/**
 * Profession.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.19
 */

public class Profession {


    public String name;
    public String profession;

    /**
     * Возвращаем имя
     *
     * @return имя.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Присваиваем имя
     *
     * @param name Имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращаем профессию
     *
     * @return профессия.
     */
    public String getProfession() {
        return this.profession;
    }

    /**
     * Присваиваем профессию
     *
     * @param profession профессия
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }
}
