package ru.job4j.list;

/**
 * Class Cycle.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.09
 */
public class Cycle {

    /**
     * Проверяем, есть ли в последовательсноти цикличность
     *
     * @param first первый элемент последовательности
     * @return true/false
     */
    public boolean hasCycle(Node first) {
        Node currentFirst = first;
        Node currentSecond = first;
        boolean result = false;
        while (currentFirst != null && currentSecond != null) {
            currentFirst = currentFirst.next;
            currentSecond = currentSecond.next;
            if (currentSecond != null) {
                currentSecond = currentSecond.next;
            } else {
                break;
            }
            if (currentFirst.equals(currentSecond)) {
                result = true;
                break;
            }
        }
        return result;
    }

}
