package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void whenMassiveHaveFiveElements() {
        BubbleSort array = new BubbleSort();
        int[] result = array.sort(new int[]{5, 1, 2, 7, 3});
        assertThat(result, is(new int[]{1, 2, 3, 5, 7}));
    }
}
