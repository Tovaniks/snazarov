package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void whenAddDuplicationThenFalse() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        String first = "first";
        String second = "second";
        assertThat(map.insert(first, 1), is(true));
        assertThat(map.get(first), is(1));
        assertThat(map.insert(second, 1), is(true));
        assertThat(map.get(second), is(1));
        assertThat(map.insert(first, 2), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddElementThenGetElements() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        String first = "first";
        String second = "second";
        String third = "third";
        map.insert(first, 1);
        map.insert(second, 2);
        map.insert(third, 3);
        Iterator iterator = map.iterator();
        assertThat(iterator.next(), is(first));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(second));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(third));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenChangeElementThenException() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        String first = "first";
        String second = "second";
        String third = "third";
        map.insert(first, 1);
        map.insert(second, 2);
        Iterator iterator = map.iterator();
        iterator.next();
        map.insert(third, 3);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddManyElementsThenResize() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        for (int index = 0; index < 1000; index++) {
            assertThat(map.insert(index, index), is(true));
        }
        Iterator iterator = map.iterator();
        for (int index = 0; index < 1000; index++) {
            assertThat(iterator.next(), is(index));
        }
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }


    @Test(expected = NoSuchElementException.class)
    public void whenDeleteElementThenChange() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        String first = "first";
        String second = "second";
        String third = "third";
        map.insert(first, 1);
        map.insert(second, 2);
        map.insert(third, 3);
        assertThat(map.delete(second), is(true));
        assertThat(map.delete("four"), is(false));
        Iterator iterator = map.iterator();
        assertThat(iterator.next(), is(first));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(third));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }
}