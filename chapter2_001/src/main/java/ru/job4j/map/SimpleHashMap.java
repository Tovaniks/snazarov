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
    private Node<K, V>[] elements = (Node<K, V>[]) new Node[16];
    private int position = 0;
    private int modCount = 0;

    /**
     * Добавляем элемент в карту
     *
     * @param key   ключ
     * @param value значение
     * @return успещность добавления
     */
    public boolean insert(K key, V value) {
        boolean success = false;
        if (position == elements.length) {
            resize();
        }
        int pos = getPosition(key);
        if (pos == -1) {
            elements[position++] = new Node<>     (key, value);
            modCount++;
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
        return getPosition(key) == -1 ? null : elements[getPosition(key)].value;
    }

    /**
     * Удаляем элемент по ключу
     *
     * @param key ключ
     * @return успещность
     */
    public boolean delete(K key) {
        boolean success = false;
        int pos = getPosition(key);
        if (pos != -1) {
            System.arraycopy(this.elements, pos + 1, this.elements, pos, position - pos - 1);
            success = true;
            position--;
            modCount++;
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
            int iterModCount = modCount;

            @Override
            public boolean hasNext() {
                return innerPosition < position;
            }

            @Override
            public K next() {
                if (iterModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[innerPosition++].key;
            }
        });
    }

    /**
     * Возвращаем номер элемента в массиве по ключу
     *
     * @param key ключ
     * @return порядковый номер
     */
    private int getPosition(K key) {
        int result = -1;
        for (int index = 0; index < position; index++) {
            if (key.equals(elements[index].key)) {
                result = index;
                break;
            }
        }
        return result;
    }

    /**
     * Меняем длину массива, если достигаем предельных значений
     */
    private void resize() {
        Node<K, V>[] newElements = (Node<K, V>[]) new Node[position * 2];
        System.arraycopy(this.elements, 0, newElements, 0, position);
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

        /**
         * Хэш-функция
         *
         * @param key ключ
         * @return возращаем хэш ключа
         */
        int hash(Object key) {
            int h = key.hashCode();
            return (key == null) ? 0 : h  ^ (h >>> 16);
        }

    }

}
