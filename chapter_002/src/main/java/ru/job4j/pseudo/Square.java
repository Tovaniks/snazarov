package ru.job4j.pseudo;

/**
 * Square
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.22
 */

public class Square implements Shape {

    /**
     * Рисуем квадрат и возвращаем в виде строки
     *
     * @return строка.
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        String separator = System.lineSeparator();
        pic.append("+++")
                .append(separator)
                .append("+ +")
                .append(separator)
                .append("+++");
        return pic.toString();
    }
}
