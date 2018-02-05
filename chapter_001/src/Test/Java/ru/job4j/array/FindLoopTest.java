package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindLoopTest {
    @Test
    public void whenElementIsFind() {
        FindLoop fl = new FindLoop();
        int result = fl.indexOf(new int[]{1, 3, 4, 0, -1, 45, 16, 1000}, -1);
        assertThat(result, is(4));
    }

    @Test
    public void whenElementIsNotFind() {
        FindLoop fl = new FindLoop();
        int result = fl.indexOf(new int[]{2, 0, 3, 4, 1, 2, 5, 6, 24}, -1);
        assertThat(result, is(-1));
    }

    @Test
    public void whenFirstElementIsFind() {
        FindLoop fl = new FindLoop();
        int result = fl.indexOf(new int[]{1, 3, 3}, 3);
        assertThat(result, is(1));
    }
}
