package ru.job4j.tree;

import java.util.*;

/**
 * Class Tree.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.20
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E>, Iterable<E> {

    private Node<E> root;
    private int modCount = 0;
    private int size;

    /**
     * Конструктор
     *
     * @param root корневой элемент
     */
    public Tree(E root) {
        this.root = new Node<>(root);
        size = 1;
    }

    /**
     * Добавляем связку родитель - дочка
     *
     * @param parent родительский элемент
     * @param child  дочерний элемент
     * @return успешность операции
     */
    @Override
    public boolean add(E parent, E child) {
        boolean success = true;
        Optional<Node<E>> node = findBy(parent);
        if (node.isPresent()) {
            for (Node<E> ch : node.get().leaves()) {
                if (ch.eqValue(child)) {
                    success = false;
                    break;
                }
            }
            if (success) {
                node.get().add(new Node<>(child));
                success = true;
                this.modCount++;
                this.size++;
            }
        }
        return success;
    }

    /**
     * Поиск элемента в дереве
     *
     * @param value элемент
     * @return родитель - дочка
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }


    /**
     * Итератор
     *
     * @return итератор по элементам дерева
     */
    @Override
    public Iterator<E> iterator() {
        return (new Iterator<E>() {

            Queue<Node<E>> innerState = new LinkedList<>();
            private int innerModCount = modCount;
            private int innerPosition = 0;

            /**
             * Проверяем наличие следующего элемента
             *
             * @return true/false
             */
            @Override
            public boolean hasNext() {
                return this.innerPosition < size;
            }

            /**
             * Возвращает следующий элемент
             *
             * @return следующий элемент в дереве
             */
            @Override
            public E next() {
                if (this.innerPosition == 0) {
                    this.innerState.offer(root);
                }
                if (this.innerModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> result = this.innerState.poll();
                this.innerPosition++;
                for (Node<E> leave : result.leaves()) {
                    this.innerState.offer(leave);
                }
                return result.getValue();
            }
        });
    }


    /**
     * Проверяем, является ли дерево бинарным
     *
     * @return true/false
     */
    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.leaves().size() > 2) {
                result = false;
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

}