package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class PrimeIterator.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.03
 */
public class PrimeIterator implements Iterator<Integer> {

    private int[] numbers;
    int position = 0;

    /**
     * Конструктор. Принимает массив int-ов
     *
     * @param ints массив элементов.
     */
    public PrimeIterator(int[] ints) {
        this.numbers = ints;
    }

    /**
     * Проверяем, есть ли впереди простые числа
     *
     * @return true/false
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int index = position; index < numbers.length; index++) {
            if (isPrime(numbers[index])) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Ищем следующее в списке простое число
     *
     * @return простое число
     */
    @Override
    public Integer next() {
        Integer result = null;
        while (position < numbers.length) {
            if (isPrime(numbers[position])) {
                result = numbers[position++];
                break;
            }
            position++;
        }
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * Проверяем является ли число простым
     *
     * @param number число
     * @return true/false
     */
    private boolean isPrime(Integer number) {
        boolean result = true;
        if (number == 1) {
            result = false;
        } else {
            for (int index = 2; index < number; index++) {
                if (number % index == 0) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
