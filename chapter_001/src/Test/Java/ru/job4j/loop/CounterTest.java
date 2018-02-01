package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CounterTest {
    @Test
    public void whenFromZeroToSixThenTwenty() {
        Counter cnt = new Counter();
        int result;
        result = cnt.add(0, 6);
        assertThat(result, is(12));
    }
}
