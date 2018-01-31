package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sergey Nazarov (tovaniks@yandex.ru)
 * @version $Id$
 * @since 2018.01.30
 */

public class MaxTest {
    @Test
    public void whenFirstIsGreaterThanTheSecond() {
        Max maximum = new Max();
        int result = maximum.max(10, 4);
        assertThat(result, is(10));
    }

    @Test
    public void whenThirdIsGreaterThanTheFirstAndSecond() {
        Max maximum = new Max();
        int result = maximum.max(10, 4,  15);
        assertThat(result, is(15));
    }


}
