package ru.job4j.list;

/**
 * Class Node.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.09
 */

public class Node<T> {
    T value;
    Node<T> next;

    /**
     * Конструктор
     *
     * @param value значение
     */
    public Node(T value) {
        this.value = value;
    }
}
