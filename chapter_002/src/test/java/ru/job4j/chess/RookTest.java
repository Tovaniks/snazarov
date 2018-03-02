package ru.job4j.chess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Queen;
import ru.job4j.chess.figures.Rook;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RookTest {

    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenRookWayThenWay() {
        Cell[] result;
        Cell start = new Cell(4, 4);
        Cell end = new Cell(7, 4);
        Rook rook = new Rook(start);
        result = rook.validWay(start, end);
        Cell[] expected = new Cell[]{start, new Cell(5, 4), new Cell(6, 4), end};
        assertThat(result.length, is(expected.length));
        for (int index = 0; index < result.length; index++) {
            assertThat(result[index].getX(), is(expected[index].getX()));
            assertThat(result[index].getY(), is(expected[index].getY()));
        }
    }

    @Test
    public void whenRookWayThenError() {
        Cell start = new Cell(4, 4);
        Cell end = new Cell(6, 1);
        Rook rook = new Rook(start);
            Cell[] path = rook.validWay(start, end);
            assertThat(
                    this.mem.toString(),
                    is(
                            String.format("Фигура не может сюда пойти!%n")
                    )
            );
    }
}
