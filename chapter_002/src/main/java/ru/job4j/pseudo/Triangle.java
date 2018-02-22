package ru.job4j.pseudo;

/**
 * Triangle
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.22
 */

public class Triangle implements Shape {

    /**
     * Рисуем треугольник и возвращаем в виде строки
     *
     * @return строка.
     */

    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        String separator = System.lineSeparator();
        pic.append("+   ")
                .append(separator)
                .append("++  ")
                .append(separator)
                .append("+++ ")
                .append(separator)
                .append("++++");
        return pic.toString();
    }
}
