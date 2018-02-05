package ru.job4j.loop;

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
        StringBuilder screen = new StringBuilder();
        int weight = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();

    }

    /**
     * Метод формирует левостороннюю пирамиду заданной высоты из символов ^ и возвращает ее в виде строки.
     *
     * @param height  высота
     * @return пирамиду в виде строки.
     */
    public String leftTrl(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= weight - column - 1) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    /**
     * Метод формирует пирамиду заданной высоты из символов ^ и возвращает ее в виде строки.
     *
     * @param height  высота
     * @return пирамиду в виде строки.
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (column >= height - row - 1 && column <= height + row - 1) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }


    public static void main(String[] args) {
        System.out.println(new Paint().pyramid(2));
    }
}
