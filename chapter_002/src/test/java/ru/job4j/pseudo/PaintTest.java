package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PaintTest {

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Test
    public void whenDrawSquare() {
        String separator = System.lineSeparator();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("+++")
                                .append(separator)
                                .append("+ +")
                                .append(separator)
                                .append("+++")
                                .append(separator)
                                .toString()
                )
        );
    }


    @Test
    public void whenDrawTriangle() {
        System.setOut(new PrintStream(out));
        String separator = System.lineSeparator();
        new Paint().draw(new Triangle());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("+   ")
                                .append(separator)
                                .append("++  ")
                                .append(separator)
                                .append("+++ ")
                                .append(separator)
                                .append("++++")
                                .append(separator)
                                .toString()
                )
        );
    }

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }
}