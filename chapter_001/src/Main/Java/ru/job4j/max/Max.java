package ru.job4j.max;

/**
 * Class Max.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.01.29
 */


public class Max {

    /**
     * Return max.
     *
     * @param first, second - two numbers.
     * @return max number.
     */

    public int max(int first, int second) {
        return first > second ? first : second;
    }

    public int max(int first, int second, int third) {
        return max(max(first, second), third);
    }
}
