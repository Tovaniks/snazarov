package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * MatrixIterator.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.03
 */
public class MatrixIterator implements Iterator {

    private int[][] array;
    private int pointX = 0;
    private int pointY = 0;

    /**
     * Конструктор. Принимает двухмерный массив.
     *
     * @param array двухмерный массив
     */
    MatrixIterator(final int[][] array) {
        this.array = array;
    }

    /**
     * Проверяем, есть ли следующий элемент в массиве
     *
     * @return true/false
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        if (pointX < array.length) {
            if (pointY < array[pointX].length) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Возвращаем элемент из массива и переводим каретку
     *
     * @return текущий элемент массива
     */
    @Override
    public Object next() {
        int result;
        if (pointX == array.length) {
            throw new NoSuchElementException();
        } else {
            result = array[pointX][pointY];
            if (pointY < array[pointX].length - 1) {
                pointY++;
            } else {
                pointX++;
                pointY = 0;
            }
        }
        return result;
    }

}