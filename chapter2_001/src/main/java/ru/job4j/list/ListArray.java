package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Store.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.09
 */

public class ListArray<E> implements Iterable<E> {

    private E[] elements = (E[]) new Object[10];
    private int position = 0;
    private int modCount = 0;

    /**
     * Добавляем значение
     *
     * @param value значение
     */
    public void add(E value) {
        if (position == elements.length) {
            E[] newElements = (E[]) new Object[position * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
        elements[position++] = value;
        modCount++;
    }

    /**
     * Ищем значение по индексу
     *
     * @param index индекс
     * @return значение
     */
    public E get(int index) {
        return index <= position ? elements[index] : null;
    }


    @Override
    public Iterator<E> iterator() {
        return (new Iterator<E>() {

            private int innerPosition = 0;
            private int innerModCount = modCount;

            /**
             * Проверяем, есть ли следующий элемент
             *
             * @return элемент
             */
            @Override
            public boolean hasNext() {
                return innerPosition < position;
            }

            /**
             * Возвращаем следующий элемент
             *
             * @return элемент
             */
            @Override
            public E next() {
                if (innerModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[innerPosition++];
            }
        });
    }
}
