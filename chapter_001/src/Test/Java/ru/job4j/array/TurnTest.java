package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TurnTest {
    @Test
    public void whenArrayHaveSixElement() {
        Turn array = new Turn();
        int[] result = array.back(new int[]{0, 1, 2, 3, 4, 5});
        assertThat(result, is(new int[]{5, 4, 3, 2, 1, 0}));
    }

    @Test
    public void whenArrayHaveSevenElement() {
        Turn array = new Turn();
        int[] result = array.back(new int[]{6, 5, 4, 3, 2, 1, 0});
        assertThat(result, is(new int[]{0, 1, 2, 3, 4, 5, 6}));
    }

}
