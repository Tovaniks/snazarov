package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test(expected = NoSuchElementException.class)
    public void whenAddDuplicateThenNotAdded() {
        SimpleSet<Integer> array = new SimpleSet<>();
        array.add(1);
        array.add(1);
        array.add(2);
        Iterator<Integer> iter = array.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(2));
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }


}