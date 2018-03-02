package ru.job4j.chess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.figures.Bishop;
import ru.job4j.chess.figures.Pawn;
import ru.job4j.chess.figures.Rook;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTest {

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
    public void whenBishopMoveThenMove() {
        Board board = new Board();
        board.add(new Bishop(new Cell(0, 0)));
        boolean result = board.validMove(new Cell(0, 0), new Cell(1, 1));
        assertThat(result, is(true));
    }

    @Test
    public void whenBishopMoveThenNotMove() {
        Board board = new Board();
        board.add(new Bishop(new Cell(0, 0)));
        boolean result = board.validMove(new Cell(0, 0), new Cell(0, 1));
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Фигура не может сюда пойти!%n")
                )
        );
    }

    @Test
    public void whenAnotherFigureInWay() {
        Board board = new Board();
        board.add(new Rook(new Cell(0, 0)));
        board.add(new Pawn(new Cell(0, 1)));
        boolean result = board.validMove(new Cell(0, 0), new Cell(0, 4));
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Путь занят фигурами!%n")
                )
        );
    }

    @Test
    public void whenFigureIsNotFound() {
        Board board = new Board();
        boolean result = board.validMove(new Cell(0, 0), new Cell(0, 4));
        assertThat(
                this.mem.toString(),
                is(
                        String.format("В изначальной клетке нет фигуры!%n")
                )
        );
    }
}
