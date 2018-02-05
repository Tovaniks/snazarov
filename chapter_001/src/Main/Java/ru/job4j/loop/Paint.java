package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Class Paint.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.05
 */

public class Paint {

    /**
     * Метод формирует правостороннюю пирамиду заданной высоты из символов ^ и возвращает ее в виде строки.
     *
     * @param height  высота
     * @return пирамиду в виде строки.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );

    }

    /**
     * Метод формирует левостороннюю пирамиду заданной высоты из символов ^ и возвращает ее в виде строки.
     *
     * @param height  высота
     * @return пирамиду в виде строки.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    /**
     * Метод формирует пирамиду заданной высоты из символов ^ и возвращает ее в виде строки.
     *
     * @param height  высота
     * @return пирамиду в виде строки.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> column >= height - row - 1 && column <= height + row - 1
        );
    }

    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

}
