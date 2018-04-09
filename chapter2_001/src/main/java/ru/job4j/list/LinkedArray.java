package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedArray<E> implements Iterable<E> {

    private Node<E> first = null;
    private Node<E> last = null;
    private int modCount = 0;
    private int size = 0;

    /**
     * Добавляем значение
     *
     * @param value значение
     */
    public void add(E value) {
        Node<E> newNode = new Node<>(last, value, null);
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
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
        E result = null;
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
    public E removeFirst() {
        E result = first.item;
        if (first.next != null) {
            first = first.next;
            first.prev = null;
        } else {
            first = null;
            last = null;
        }
        size--;
        modCount++;
        return result;
    }

    /**
     * Удаляем последний элемент и возращаем его значение
     *
     * @return значение последнего элемента
     */
    public E removeLast() {
        E result = last.item;
        if (last.prev != null) {
            last = last.prev;
            last.next = null;
        } else {
            first = null;
            last = null;
        }
        size--;
        modCount++;
        return result;
    }


    @Override
    public Iterator<E> iterator() {
        return (new Iterator<E>() {

            private int innerPosition = 0;
            private int innerModCount = modCount;
            private Node<E> currentElement = first;

            /**
             * Проверяем, есть ли следующий элемент
             *
             * @return элемент
             */
            @Override
            public boolean hasNext() {
                return innerPosition < size;
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
                E result = currentElement.item;
                currentElement = currentElement.next;
                innerPosition++;
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
