package ru.job4j.array;

/**
 * Class Square.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.05
 */

public class Square {

    /**
     * Метод формирует массив заданной длины.
     *
     * @param bound  длина массива
     * @return массив.
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 0; i < bound; i++) {
            rst[i] = (int) Math.pow((i + 1), 2);
        }
        return rst;
    }
}
