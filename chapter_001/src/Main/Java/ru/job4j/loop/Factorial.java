package ru.job4j.loop;

/**
 * Class Counter.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.01
 */

public class Factorial {

    /**
     * Метод вычисляет факториал заданного числа.
     *
     * @param n число, от которого ищем факториал
     * @return факториал.
     */

    public int calc(int n) {
        int result = n;
        if (n != 0) {
            result = result * calc(n - 1);
        } else {
            result = 1;
        }
        return result;
    }
}
