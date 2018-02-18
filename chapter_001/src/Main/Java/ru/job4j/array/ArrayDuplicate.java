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
        int end = array.length;
        for (int i = 0; i < end; i++) {
            for (int j = i + 1; j < end; j++) {
                if (array[i].equals(array[j])) {
                    swap = array[end - 1];
                    array[end - 1] = array[j];
                    array[j] = swap;
                    end--;
                }
            }
        }
        return Arrays.copyOf(array, end);
    }
}
