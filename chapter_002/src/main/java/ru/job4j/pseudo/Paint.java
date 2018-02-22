package ru.job4j.pseudo;

/**
 * Triangle
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.22
 */

public class Paint {

    /**
     * Рисуем фигуру
     *
     * @param shape фигура
     */

    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }
}
