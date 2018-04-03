package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Class EvenNumbersIterator.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.03
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] numbers;
    int position = 0;

    /**
     * Конструктор. Принимает массив int-ов
     *
     * @param ints массив элементов.
     */
    public EvenNumbersIterator(int[] ints) {
        this.numbers = ints;
    }

    /**
     * Проверяем, есть ли впереди четные элементы
     *
     * @return true/false
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int index = position; index < numbers.length; index++) {
            if (numbers[index] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Ищем следующий четный элемент
     *
     * @return четный элемент
     */
    @Override
    public Integer next() {
        int result = -1;
        while (position < numbers.length) {
            if (numbers[position] % 2 == 0) {
                result = numbers[position++];
                break;
            }
            position++;
        }
        if (result < 0) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
