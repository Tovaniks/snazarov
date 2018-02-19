package ru.job4j.profession;


/**
 * Teacher.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.19
 */


public class Teacher extends Profession {
    /**
     * Учим студента
     *
     * @param studient студент
     * @return описание процесса.
     */
    public String teach(Studient studient) {
        return this.getProfession() + " " + this.getName() + " учит " + studient.getName();

    }
}
