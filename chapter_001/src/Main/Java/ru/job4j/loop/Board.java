package ru.job4j.loop;

/**
 * Class Counter.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.01
 */

public class Board {

    /**
     * Метод щахматную доску заданной длины строкой
     *
     * @param width  - ширина
     * @param height - высота
     * @return заполненная строка.
     */

    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 1; i <= height; i++) {
            for (int y = 1; y <= width; y++) {
                if ((i + y) % 2 == 0) {
                    screen.append("x");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }

}
