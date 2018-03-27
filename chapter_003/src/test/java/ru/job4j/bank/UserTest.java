package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {
    @Test
    public void whenTwoUserIsEquals() {
        User first = new User("Sergey", "12345");
        User second = new User("Sergey", "12345");
        boolean result = first.equals(second);
        assertThat(result, is(true));
    }
}
