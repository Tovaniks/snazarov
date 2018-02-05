package ru.job4j.array;

/**
 * Class FindLoop.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.05
 */


public class FindLoop {

    /**
     * Метод перебором ищет в массиве заданный элемент и возвращает его индекс или -1, если элемент не найден.
     *
     * @param data массив
     * @param el елемент
     * @return индекс.
     */
    public int indexOf(int[] data, int el) {
        int result = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == el) {
                result = i;
                break;
            }
        }
        return result;
    }

}
