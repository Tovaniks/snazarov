package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {
    @Test
    public void whenArrayLenIsSix() {
        Square sqr = new Square();
        int[] result = sqr.calculate(6);
        assertThat(result, is(new int[]{1, 4, 9, 16, 25, 36}));
    }

    @Test
    public void whenArrayLenIsFour() {
        Square sqr = new Square();
        int[] result = sqr.calculate(4);
        assertThat(result, is(new int[]{1, 4, 9, 16}));
    }
}
