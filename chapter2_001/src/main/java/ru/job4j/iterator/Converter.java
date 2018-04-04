package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Converter.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.04
 */
public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> its) {
        return new Iterator<Integer>() {

            private Iterator<Integer> currentState = its.hasNext() ? its.next() : null;
            private Integer nextElement = currentState.hasNext() ? currentState.next() : null;

            /**
             * Проверяем, есть ли следующий элемент в списке
             *
             * @return true/false
             */
            @Override
            public boolean hasNext() {
                return nextElement != null;
            }

            /**
             * Возвращает следующий элемент
             *
             * @return следующий элемент в списке списков
             */
            @Override
            public Integer next() {
                Integer result = nextElement;
                if (result == null) {
                    throw new NoSuchElementException();
                }
                if (!currentState.hasNext() && its.hasNext()) {
                    currentState = its.next();
                }
                nextElement = currentState.hasNext() ? currentState.next() : null;
                return result;
            }
        };
    }
}
