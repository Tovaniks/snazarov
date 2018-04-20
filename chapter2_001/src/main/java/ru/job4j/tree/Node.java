package ru.job4j.tree;


import java.util.ArrayList;
import java.util.List;


/**
 * Class Node.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.20
 */
public class Node<E extends Comparable<E>> {
    private final List<Node<E>> children = new ArrayList<>();
    private final E value;

    /**
     * Конструктор
     *
     * @param value значение ячейки
     */
    public Node(final E value) {
        this.value = value;
    }


    /**
     * Добавляем потомка
     *
     * @param child потомок
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }


    /**
     * Возвращаем список с листьями ячейки
     *
     * @return список
     */
    public List<Node<E>> leaves() {
        return this.children;
    }


    /**
     * Сравниваем значения
     *
     * @param that параметр, с которым сравниваем
     * @return результат сравнение -1/0/1
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    /**
     * Возвращаем значение ноды
     *
     * @return значение ноды
     */
    public E getValue() {
        return this.value;
    }

}
