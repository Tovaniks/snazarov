package ru.job4j.array;

/**
 * Test.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.07
 */

public class BubbleSort {

    /**
     * Метод сортирует массив от меньшего к большему.
     *
     * @param array массив
     * @return отсортированный массив.
     */

    public int[] sort(int[] array) {
        int check;
        int swap;
        do {
            check = 0;

            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    swap = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = swap;
                    check = 1;
                }
            }
        }
        while (check != 0);
        return array;
    }

}
