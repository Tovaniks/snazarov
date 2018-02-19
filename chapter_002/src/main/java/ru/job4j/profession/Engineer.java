package ru.job4j.profession;


/**
 * Engineer.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.19
 */


public class Engineer extends Profession {
    /**
     * Строим здание
     *
     * @param house дом
     * @return описание процесса.
     */
    public String build(House house) {
        return this.getProfession() + " " + this.getName() + " строит " + house.getType();
    }
}
