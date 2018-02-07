package ru.job4j.array;

import java.util.Arrays;

/**
 * ArrayDuplicate.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.07
 */

public class ArrayDuplicate {

    /**
     * Удаляем в массиве дубли
     *
     * @param array массив
     * @return массив.
     */
    public String[] remove(String[] array) {
        String swap;
        int endOfMassive = array.length;
        for (int i = 0; i < endOfMassive; i++) {
            for (int j = i + 1; j < endOfMassive; j++) {
                if (array[i].equals(array[j])) {
                    swap = array[endOfMassive - 1];
                    array[endOfMassive - 1] = array[j];
                    array[j] = swap;
                    endOfMassive--;
                }
            }
        }
        return Arrays.copyOf(array, endOfMassive);
    }
}
