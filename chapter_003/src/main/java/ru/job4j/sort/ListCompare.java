package ru.job4j.sort;

import java.util.Comparator;

/**
 * ListCompare
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.16
 */
public class ListCompare implements Comparator<String> {


    /**
     * Сравниваем посимвольно два слова
     *
     * @param left  первой слово
     * @param right второе слово
     * @return -1 - первое слово перед вторым, 0 - слова идентичны, 1 - второе слово перед первым
     */
    @Override
    public int compare(String left, String right) {
        char[] leftArray = left.toCharArray();
        char[] rightArray = right.toCharArray();
        int result = 0;
        for (int index = 0; index < Math.min(leftArray.length, rightArray.length); index++) {
            if (Character.compare(leftArray[index], rightArray[index]) != 0) {
                result = Character.compare(leftArray[index], rightArray[index]);
                break;
            }
        }
        return result == 0 ? Integer.compare(leftArray.length, rightArray.length) : result;
    }
}