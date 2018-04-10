package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashSetTest {

    @Test
    public void whenAddMoreThenSixteenElements() {
        SimpleHashSet<Integer> array = new SimpleHashSet<>();
        for (int index = 0; index <= 20; index++) {
            array.add(index);
        }
        for (int index = 0; index <= 20; index++) {
            assertThat(array.contains(index), is(true));
        }
    }


    @Test
    public void whenRemoveElementThenContainsIsFalse() {
        SimpleHashSet<Integer> array = new SimpleHashSet<>();
        array.add(1);
        assertThat(array.contains(1), is(true));
        array.add(2);
        assertThat(array.contains(2), is(true));
        array.remove(2);
        assertThat(array.contains(2), is(false));
        assertThat(array.remove(2), is(false));
    }

    @Test
    public void whenAddDuplicateThenReturnFalse() {
        SimpleHashSet<Integer> array = new SimpleHashSet<>();
        assertThat(array.add(1), is(true));
        assertThat(array.add(1), is(false));
    }

}