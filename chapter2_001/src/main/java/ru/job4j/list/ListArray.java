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
    @GuardedBy("this")
    private int position = 0;
    @GuardedBy("this")
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

    private synchronized int getModCount() {
        return this.modCount;
    }

    private synchronized int getPosition() {
        return this.position;
    }


    @Override
    public Iterator<E> iterator() {
        return (new Iterator<E>() {

            private int innerPosition = 0;
            private int innerModCount = getModCount();
            private E[] innerElements = getElements();

            /**
             * Проверяем, есть ли следующий элемент
             *
             * @return элемент
             */
            @Override
            public boolean hasNext() {
                return innerPosition < getPosition();
            }

            /**
             * Возвращаем следующий элемент
             *
             * @return элемент
             */
            @Override
            public E next() {
                E result;
                synchronized (this) {
                    if (innerModCount != getModCount()) {
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
