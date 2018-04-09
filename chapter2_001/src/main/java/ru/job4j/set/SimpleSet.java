package ru.job4j.set;

import ru.job4j.generic.SimpleArray;

import java.util.Iterator;

/**
 * Class SimpleSet.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.09
 */
public class SimpleSet<E> implements Iterable<E> {

    private SimpleArray<E> array = new SimpleArray<>();

    /**
     * Добавляем элемент. Если элемент есть в списке, добавление не происходит
     *
     * @param value значение
     */
    public void add(E value) {
        boolean right = true;
        for (E element : array) {
            if (element.equals(value)) {
                right = false;
                break;
            }
        }
        if (right) {
            array.add(value);
        }
    }


    /**
     * Итератор
     *
     * @return итератор
     */
    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }
}
