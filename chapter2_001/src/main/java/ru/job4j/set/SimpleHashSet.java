package ru.job4j.set;

/**
 * Class SimpleHashSet.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.10
 */
public class SimpleHashSet<E> {

    private Node<E>[] elements = new Node[16];
    private int position = 0;


    /**
     * Добавляем элемент в список. Если элемент уже присутствует, то добавление не происходит.
     *
     * @param value элемент
     * @return true/false
     */
    public boolean add(E value) {
        boolean success = !contains(value);
        if (position == elements.length) {
            Node<E>[] newElements = new Node[position * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
        if (success) {
            elements[position++] = new Node<>(value);
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
        return getPosition(value) != null;
    }


    /**
     * Удаляем элемент из списка
     *
     * @param value элемент
     * @return true/false успешности операции
     */
    public boolean remove(E value) {
        int index;
        boolean success = false;
        if (getPosition(value) != null) {
            success = true;
            index = getPosition(value);
            System.arraycopy(this.elements, index + 1, this.elements, index, this.elements.length - index - 1);
            position--;
        }
        return success;
    }

    /**
     * Возвращаем позицию элемента в списке
     *
     * @param value элемент
     * @return позиция
     */
    private Integer getPosition(E value) {
        Integer result = null;
        for (int index = 0; index < position; index++) {
            if (value.hashCode() == elements[index].hash && value.equals(elements[index].value)) {
                result = index;
                break;
            }
        }
        return result;
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
            this.hash = value.hashCode();
            this.value = value;
        }
    }


}
