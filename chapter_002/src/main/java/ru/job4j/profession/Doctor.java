package ru.job4j.profession;


/**
 * Doctor.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.19
 */


public class Doctor extends Profession {

    /**
     * Доктор лечит пациента
     *
     * @param patient пациент
     * @return описание процесса.
     */

    public String heal(Patient patient) {
        return this.getProfession() + " " + this.getName() + " лечит " + patient.getName();
    }
}
