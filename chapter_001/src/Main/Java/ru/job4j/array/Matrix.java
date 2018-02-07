package ru.job4j.array;

/**
 * Matrix.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.07
 */

public class Matrix {

    /**
     * Строим таблицу умножения
     *
     * @param size размер матрицы
     * @return массив.
     */

    public int[][] multiple(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
            }
        }
        return matrix;
    }
}

