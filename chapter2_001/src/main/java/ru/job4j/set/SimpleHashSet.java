package ru.job4j.set;

/**
 * Class SimpleHashSet.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.10
 */
public class SimpleHashSet<E> {

    private int count = 0;
    private int size = 16;
    private Node<E>[] elements = (Node<E>[]) new Node[size];


    /**
     * Добавляем элемент в список. Если элемент уже присутствует, то добавление не происходит.
     *
     * @param value элемент
     * @return true/false
     */
    public boolean add(E value) {
        if (this.count == elements.length) {
            resize();
        }
        boolean success = !contains(value);
        if (success) {
            elements[indexFor(value)] = new Node<>(value);
            count++;
        }
        return success;
    }


    /**
     * Проверяем наличие элемента в списке.
     *
     * @param value элемент.
     * @return true/false присутствия элемента.
     */
    public boolean contains(E value) {
        boolean success = false;
        if (elements[indexFor(value)] != null && elements[indexFor(value)].value.equals(value)) {
            success = true;
        }
        return success;
    }


    /**
     * Удаляем элемент из списка
     *
     * @param value элемент
     * @return true/false успешности операции
     */
    public boolean remove(E value) {
        int index = indexFor(value);
        boolean success = false;
        if (elements[index] != null) {
            success = true;
            elements[index] = null;
            this.count--;
        }
        return success;
    }

    /**
     * Нода из хеша и объекта.
     */
    private class Node<E> {
        int hash;
        E value;

        /**
         * Конструктор. В конструкторе добавляется элемент и инициилизируется значение хэша.
         *
         * @param value элемент.
         */
        Node(E value) {
            this.hash = hash(value);
            this.value = value;
        }
    }

    /**
     * Хэш-функция
     *
     * @param value значение
     * @return хэш
     */
    private int hash(Object value) {
        int h = value.hashCode();
        return (value == null) ? 0 : h ^ (h >>> 16);
    }


    /**
     * Ищем место в массиве
     *
     * @param value значение
     * @return место в массиве
     */
    private int indexFor(E value) {
        return hash(value) & (size - 1);
    }

    /**
     * Увеличиваем массив вдвое
     */
    private void resize() {
        size *= 2;
        Node<E>[] newElements = (Node<E>[]) new Node[size];
        for (Node<E> element : elements) {
            if (element != null) {
                newElements[indexFor(element.value)] = element;
            }
        }
        elements = newElements;
    }


}
