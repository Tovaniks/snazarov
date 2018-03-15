package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * ConvertList.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.14
 */
public class ConvertList {
    /**
     * Конвертируем массив в List
     *
     * @param array массив
     * @return List
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>();
        for (int[] anArray : array) {
            for (int anAnArray : anArray) {
                result.add(anAnArray);
            }
        }
        return result;
    }

    /**
     * Конвертируем List в список с заданным кол-вом колонок. Если в List не хватает элементов, заполняется 0
     *
     * @param list список
     * @param rows кол-во колонок
     * @return массив
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int columns = (int) Math.ceil(list.size() / (double) rows);
        int[][] result = new int[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                result[i][j] = i * rows + j < list.size() ? list.get(i * rows + j) : 0;
            }
        }
        return result;
    }


    /**
     * Конвертируем лист массивов в один лист Integer
     *
     * @param list лист массивов
     * @return новый список
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] massive : list) {
            for (int element : massive) {
                result.add(element);
            }
        }
        return result;
    }


}
