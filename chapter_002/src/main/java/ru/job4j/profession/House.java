package ru.job4j.profession;


/**
 * House.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.19
 */


public class House {


    public String type;

    /**
     * Получаем тип здания
     *
     * @return тип здания
     */
    public String getType() {
        return this.type;
    }

    /**
     * Указываем тип здания
     *
     * @param type тип здания
     */
    public void setType(String type) {
        this.type = type;
    }
}
