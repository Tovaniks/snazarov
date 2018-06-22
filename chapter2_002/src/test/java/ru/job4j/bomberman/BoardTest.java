package ru.job4j.bomberman;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void test() throws InterruptedException {
        Board board = new Board();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                board.move(new Cell(4, 4), new Cell(8, 4));
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                board.move(new Cell(8, 4), new Cell(4, 4));
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}