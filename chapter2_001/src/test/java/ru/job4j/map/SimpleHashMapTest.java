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
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        Integer first = 1;
        Integer second = 3;
        Integer third = 2;
        map.insert(first, 1);
        map.insert(second, 2);
        map.insert(third, 3);
        Iterator iterator = map.iterator();
        assertThat(iterator.next(), is(first));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(third));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(second));
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
        for (int index = 0; index < 17; index++) {
            assertThat(map.insert(index, index), is(true));
        }
        Iterator iterator = map.iterator();
        for (int index = 0; index < 17; index++) {
            assertThat(iterator.next(), is(index));
        }
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }


    @Test(expected = NoSuchElementException.class)
    public void whenDeleteElementThenChange() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        Integer first = 1;
        Integer second = 2;
        Integer third = 3;
        map.insert(first, 1);
        map.insert(second, 2);
        map.insert(third, 3);
        assertThat(map.delete(second), is(true));
        assertThat(map.delete(4), is(false));
        Iterator iterator = map.iterator();
        assertThat(iterator.next(), is(first));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(third));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenUseNextMoreThenElementsThenException() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert(null, 1);
        map.insert("ghb", 1);
        map.insert("113", 1);
        map.insert("fdf", 1);
        map.insert("113", 1);
        Iterator iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
        iterator.next();

    }
}