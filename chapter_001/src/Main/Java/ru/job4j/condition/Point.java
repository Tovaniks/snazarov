package ru.job4j.condition;

/**
 * Class Point.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.01.29
 */


public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * distanceTo.
     *
     * @param that - Point to
     */
    public double distanceTo(Point that) {
        return Math.sqrt(
                Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2)
        );
    }

}