package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LinkedArrayTest {
    @Test(expected = NoSuchElementException.class)
    public void whenAddThreeElementsThenGetTreeElements() {
        LinkedArray<String> list = new LinkedArray<>();
        String first = "first";
        String second = "second";
        String third = "third";
        list.add(first);
        list.add(second);
        list.add(third);
        Iterator iterator = list.iterator();
        assertThat(true, is(iterator.hasNext()));
        assertThat(first, is(iterator.next()));
        assertThat(true, is(iterator.hasNext()));
        assertThat(second, is(iterator.next()));
        assertThat(true, is(iterator.hasNext()));
        assertThat(third, is(iterator.next()));
        assertThat(false, is(iterator.hasNext()));
        iterator.next();
    }

    @Test
    public void whenAddElevenElementsThenAdded() {
        LinkedArray<Integer> list = new LinkedArray<>();
        for (int index = 0; index < 11; index++) {
            list.add(index);
        }
        int position = 0;
        for (Integer id : list) {
            assertThat(id, is(position++));
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenChangeElementThenException() {
        LinkedArray<String> list = new LinkedArray<>();
        String first = "first";
        String second = "second";
        String third = "third";
        list.add(first);
        list.add(second);
        list.add(third);
        Iterator iterator = list.iterator();
        iterator.next();
        list.add("Привет");
        iterator.next();
    }


}