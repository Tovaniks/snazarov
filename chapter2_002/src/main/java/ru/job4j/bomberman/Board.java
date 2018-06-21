package ru.job4j.bomberman;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class Board.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.06.22
 */
public class Board {

    private final ReentrantLock[][] board = new ReentrantLock[10][10];
    private final ArrayList<Angle> kfs = new ArrayList<>();

    /**
     * Конструктор.
     * Заполняется список коэффициентов и игровое поле.
     */
    public Board() {
        kfs.add(new Angle(1, 0));
        kfs.add(new Angle(0, 1));
        kfs.add(new Angle(-1, 0));
        kfs.add(new Angle(0, -1));
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = new ReentrantLock();
            }
        }
    }

    /**
     * Двигаем фигуру
     *
     * @param source начало пути
     * @param dest   конец пути
     */
    public void move(Cell source, Cell dest) {
        Cell current = source;
        int x, y, kfX, kfY, count, index;
        board[source.getX()][source.getY()].lock();
        while (!current.equals(dest)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            kfX = Integer.compare(dest.getX(), current.getX());
            kfY = Integer.compare(dest.getY(), current.getY());
            index = kfX != 0 ? kfs.indexOf(new Angle(kfX, 0)) : kfs.indexOf(new Angle(0, kfY));
            x = current.getX() + kfs.get(index).getX();
            y = current.getY() + kfs.get(index).getY();
            count = 0;
            while (!tryStep(x, y)) {
                count++;
                index = index + count - (kfs.size() >= index + count ? 0 : kfs.size());
                x = current.getX() + kfs.get(index).getX();
                y = current.getY() + kfs.get(index).getY();
            }
            board[current.getX()][current.getY()].unlock();
            current = new Cell(x, y);
        }
    }

    /**
     * Проверяем, можно ли занять точку x.y.
     *
     * @param x - x
     * @param y - y
     * @return true/false
     */
    private boolean tryStep(final int x, final int y) {
        boolean successful = false;
        try {
            successful = board[x][y].tryLock(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return successful;
    }

    /**
     * Класс, отражающий коэффиенты углов движения
     */
    private class Angle {
        private final int x;
        private final int y;

        /**
         * Конструктор
         *
         * @param x - x
         * @param y - y
         */
        private Angle(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Возвращаем значение x
         *
         * @return x
         */
        int getX() {
            return x;
        }

        /**
         * Возвращаем значение y
         *
         * @return y
         */
        int getY() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            boolean successful = false;
            if (obj instanceof Angle) {
                Angle angle = (Angle) obj;
                successful = angle.getX() == this.getX() && angle.getY() == this.getY();
            }
            return successful;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + x;
            result = 31 * result + y;
            return result;
        }
    }

}
