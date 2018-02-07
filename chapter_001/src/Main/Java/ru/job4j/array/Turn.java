package ru.job4j.array;

/**
 * Turn.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.06
 */

public class Turn {

    /**
     * Метод возвращает перевернутый массив.
     *
     * @param array  массив
     * @return перевернутый массив.
     */
    public int[] back(int[] array) {
        int pop;
        for (int i = 0; i < array.length / 2; i++) {
            pop = array[array.length - 1 - i];
            array[array.length - 1 - i] = array[i];
            array[i] = pop;
        }
        return array;
    }
}
