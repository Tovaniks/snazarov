package ru.job4j.tree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Class BinaryTreeSearch.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.20
 */
public class BinaryTreeSearch<E extends Comparable<? super E>> implements Iterable<E> {

    private Node<E> root;
    private int size = 0;
    private int modCount = 0;

    /**
     * Добавляю элемент в бинарное дерево
     *
     * @param value элемент
     * @return успешность добавления
     */
    public boolean add(E value) {
        boolean success;
        success = root == null ? createRoot(value) : createChildren(value);
        if (success) {
            modCount++;
            size++;
        }
        return success;
    }

    /**
     * Добавляю корневой элемент
     *
     * @param value элемент
     * @return успешность добавления
     */
    private boolean createRoot(E value) {
        root = new Node<>(value, null);
        return true;
    }

    /**
     * Добавляю элемент, который не является корневым
     *
     * @param value элемент
     * @return успешность добавления
     */
    private boolean createChildren(E value) {
        Node<E> currentNode = root;
        Node<E> parent = null;
        boolean success = true;
        do {
            if (value.compareTo(currentNode.value) == 0) {
                success = false;
                break;
            } else {
                parent = currentNode;
                if (value.compareTo(currentNode.value) < 0) {
                    currentNode = currentNode.left;
                } else {
                    currentNode = currentNode.right;
                }
            }
        } while (currentNode != null);
        if (success) {
            currentNode = new Node<>(value, parent);
            if (parent.value.compareTo(currentNode.value) > 0) {
                parent.left = currentNode;
            } else {
                parent.right = currentNode;
            }
        }
        return success;
    }

    @Override
    public Iterator iterator() {
        return (new Iterator<E>() {
            private int innerModCount = modCount;
            private Node<E> next = null;
            private int innerCount = 0;

            /**
             * Проверяю наличие следующего элемента
             *
             * @return true/false
             */
            @Override
            public boolean hasNext() {
                return innerCount < size;
            }

            /**
             * Возвращаю следующий элемент
             * @return следующий элемент
             */
            @Override
            public E next() {
                checkException();
                if (next == null) {
                    next = getLowest(root);
                }
                E result = next.value;
                innerCount++;
                initNext();
                return result;
            }

            /**
             * Проверяю наличие ошибок при итерирование по бинарному дереву
             */
            private void checkException() {
                if (innerModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (innerCount == size) {
                    throw new NoSuchElementException();
                }
            }

            /**
             * Инициирую следующий элемент
             */
            private void initNext() {
                if (next.right != null) {
                    next = getLowest(next.right);
                } else {
                    E currentValue = next.value;
                    do {
                        next = next.parent;
                    }
                    while (hasNext() && currentValue.compareTo(next.value) > 0);
                }
            }

            /**
             * Возвращаю наименьшее значение от заданого элемента
             * @param start начала поиска
             * @return значение
             */
            private Node<E> getLowest(Node<E> start) {
                Node<E> currentItem = start;
                Node<E> result = currentItem;
                if (currentItem != null) {
                    while (currentItem != null) {
                        result = currentItem;
                        currentItem = currentItem.left;
                    }
                }
                return result;
            }
        });
    }

    /**
     * Нода
     *
     * @param <E>
     */
    private class Node<E> {
        Node<E> left;
        Node<E> right;
        Node<E> parent;
        E value;

        /**
         * Конструктор
         *
         * @param value  значение ноды
         * @param parent родитель
         */
        private Node(E value, Node<E> parent) {
            this.value = value;
            this.parent = parent;
        }
    }
}
