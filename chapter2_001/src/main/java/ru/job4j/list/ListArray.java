package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class ListArray.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.09
 */
@ThreadSafe
public class ListArray<E> implements Iterable<E> {

    @GuardedBy("this")
    private E[] elements = (E[]) new Object[10];
    private int position = 0;
    private int modCount = 0;

    /**
     * Добавляем значение
     *
     * @param value значение
     */
    public synchronized void add(E value) {
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
    public synchronized E get(int index) {
        return index <= position ? elements[index] : null;
    }

    private synchronized E[] getElements() {
        return this.elements;
    }


    @Override
    public Iterator<E> iterator() {
        return (new Iterator<E>() {

            private int innerPosition = 0;
            private int innerModCount = modCount;
            private E[] innerElements = getElements();

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
            public  E next() {
                E result;
                synchronized (this) {
                    if (innerModCount != modCount) {
                        throw new ConcurrentModificationException();
                    }
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    result = innerElements[innerPosition++];
                }
                return result;
            }
        });
    }
}
