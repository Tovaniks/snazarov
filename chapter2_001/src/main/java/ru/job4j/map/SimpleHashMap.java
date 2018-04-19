package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleHashMap.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.13
 */
public class SimpleHashMap<K, V> implements Iterable<K> {
    private int size = 16;
    private int count = 0;
    private int modCount = 0;
    private Node<K, V>[] elements = (Node<K, V>[]) new Node[size];


    /**
     * Добавляем элемент в карту
     *
     * @param key   ключ
     * @param value значение
     * @return успещность добавления
     */
    public boolean insert(K key, V value) {
        boolean success = false;
        if (count == elements.length) {
            resize();
        }
        int pos = indexFor(key);
        if (elements[pos] == null) {
            elements[pos] = new Node(key, value);
            if (size < (hash(key) & (elements.length - 1))) {
                size = hash(key) & (elements.length - 1);
            }
            modCount++;
            count++;
            success = true;
        }
        return success;
    }

    /**
     * Возвращаем значение элемента по ключу
     *
     * @param key ключ
     * @return значение
     */
    public V get(K key) {
        return elements[indexFor(key)] == null ? null : elements[indexFor(key)].value;
    }


    /**
     * Удаляем элемент по ключу
     *
     * @param key ключ
     * @return успещность
     */
    public boolean delete(K key) {
        boolean success = false;
        int pos = indexFor(key);
        if (elements[pos] != null) {
            System.arraycopy(this.elements, pos + 1, this.elements, pos, size - pos - 1);
            count--;
            modCount++;
            success = true;
        }
        return success;
    }


    /**
     * Итератор
     *
     * @return возвращаем итератор по карте
     */
    @Override
    public Iterator<K> iterator() {
        return (new Iterator<K>() {

            int innerPosition = 0;
            int innerModCount = modCount;
            K next;

            @Override
            public boolean hasNext() {
                return next == null && innerPosition < size && count > 0 || next != null;
            }

            @Override
            public K next() {
                K result;
                if (innerModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (next == null && innerPosition == 0) {
                    innerPositionRun();
                    next = elements[innerPosition++].key;
                }
                result = next;
                next = null;
                innerPositionRun();
                if (innerPosition >= size) {
                    throw new NoSuchElementException();
                } else {
                    next = elements[innerPosition++].key;
                }
                return result;
            }

            private void innerPositionRun() {
                while (hasNext() && elements[innerPosition] == null) {
                    innerPosition++;
                }
            }
        });


    }


    /**
     * Увеличиваем массив вдвое
     */
    private void resize() {
        Node<K, V>[] newElements = (Node<K, V>[]) new Node[size * 2];
        System.arraycopy(this.elements, 0, newElements, 0, size);
        size *= 2;
        elements = newElements;
    }


    /**
     * Нода карты
     *
     * @param <K> ключ
     * @param <V> значение
     */
    private final class Node<K, V> {

        int hash;
        K key;
        V value;

        /**
         * Конструктор
         *
         * @param key   ключ
         * @param value значение
         */
        Node(K key, V value) {
            this.hash = hash(key);
            this.key = key;
            this.value = value;
        }
    }


    /**
     * Хэш-функция
     *
     * @param key значение
     * @return хэш
     */
    private int hash(Object key) {
        int h = key.hashCode();
        return (key == null) ? 0 : h ^ (h >>> 16);
    }


    /**
     * Поиск места в карте
     *
     * @param key ключ
     * @return индекс
     */
    private int indexFor(K key) {
        return hash(key) & (elements.length - 1);
    }
}
