package ru.job4j.generic;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class SimpleArray.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.06
 */
public class SimpleArray<T> implements Iterable<T> {

    private T[] array = (T[]) new Object[10];
    private int capacity = 10;
    private int position = 0;


    /**
     * Добавляем элемент в массив
     *
     * @param model элемент
     */
    public void add(T model) {
        if (position == capacity) {
            capacity *= 2;
            T[] dest = (T[]) new Object[capacity];
            System.arraycopy(array, 0, dest, 0, array.length);
            array = dest;
        }
        array[position++] = model;
    }


    /**
     * Меняем элемент в массиве по индексу
     *
     * @param index индекс
     * @param model новое значение
     */
    public void set(int index, T model) {
        array[index] = model;
    }


    /**
     * Удаляем элемент по индексу
     *
     * @param index индекс
     */
    public void delete(int index) {
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        position--;
    }

    /**
     * Возвращаем элемент по индексу
     *
     * @param index индекс
     * @return элемент
     */
    public T get(int index) {
        return array[index];
    }

    /**
     * Итератор
     *
     * @return итератор
     */
    @Override
    public Iterator<T> iterator() {
        return (new Iterator<T>() {
            private int arrayPosition = 0;

            /**
             * Проверяем наличие следующего элемента
             *
             * @return true/false
             */
            @Override
            public boolean hasNext() {
                return arrayPosition < position;
            }

            /**
             * Возвращаем следующий элемент
             *
             * @return следующий элемент в списке
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[arrayPosition++];
            }
        });

    }
}