package ru.job4j.loop;

/**
 * Class Counter.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.01
 */


public class Counter {

    /**
     * Метод вычисляет сумму четных чисел в промежутке между двумя заданными числами.
     *
     * @param start  начало
     * @param finish конец
     * @return сумма.
     */

    public int add(int start, int finish) {
        int result = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                result = result + i;
            }
        }
        return result;
    }
}
