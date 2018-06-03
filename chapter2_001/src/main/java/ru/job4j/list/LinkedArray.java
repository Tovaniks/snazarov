package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class LinkedArray<E> implements Iterable<E> {

    @GuardedBy("this")
    private volatile Node<E> first = null;
    @GuardedBy("this")
    private Node<E> last = null;
    @GuardedBy("this")
    private int modCount = 0;
    @GuardedBy("this")
    private int size = 0;

    /**
     * Добавляем значение
     *
     * @param value значение
     */
    public synchronized void add(E value) {
        Node<E> newNode = new Node<>(this.last, value, null);
        if (this.first == null) {
            this.first = newNode;
        } else {
            this.last.next = newNode;
        }
        this.last = newNode;
        modCount++;
        size++;
    }

    /**
     * Ищем значение по индексу
     *
     * @param index индекс
     * @return значение
     */
    public E get(int index) {
        Node<E> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    /**
     * Удаляем первый элемент и возвращаем его значение
     *
     * @return значение первого элемента
     */
    public synchronized E removeFirst() {
        E result = this.first.item;
        if (this.first.next != null) {
            this.first = this.first.next;
            this.first.prev = null;
        } else {
            this.first = null;
            this.last = null;
        }
        this.size--;
        this.modCount++;
        return result;
    }

    /**
     * Удаляем последний элемент и возращаем его значение
     *
     * @return значение последнего элемента
     */
    public synchronized E removeLast() {
        E result = this.last.item;
        if (this.last.prev != null) {
            this.last = this.last.prev;
            this.last.next = null;
        } else {
            this.first = null;
            this.last = null;
        }
        this.size--;
        this.modCount++;
        return result;
    }

    private synchronized int getModCount() {
        return this.modCount;
    }

    private synchronized int getSize() {
        return this.size;
    }


    @Override
    public Iterator<E> iterator() {
        return (new Iterator<E>() {

            private int innerPosition = 0;
            private int innerModCount = getModCount();
            private Node<E> currentElement = first;

            /**
             * Проверяем, есть ли следующий элемент
             *
             * @return элемент
             */
            @Override
            public boolean hasNext() {
                return innerPosition < getSize();
            }

            /**
             * Возвращаем следующий элемент
             *
             * @return элемент
             */
            @Override
            public E next() {
                if (this.innerModCount != getModCount()) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = this.currentElement.item;
                this.currentElement = this.currentElement.next;
                this.innerPosition++;
                return result;
            }
        });
    }


    private static class Node<E> {
        E item;
        LinkedArray.Node<E> next;
        LinkedArray.Node<E> prev;

        /**
         * Конструктор ноды
         *
         * @param prev    предыдущая нода
         * @param element значение
         * @param next    следующая нода
         */
        Node(LinkedArray.Node<E> prev, E element, LinkedArray.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
