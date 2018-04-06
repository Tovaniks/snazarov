package ru.job4j.generic;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleArrayTest {

    @Test
    public void whenAddTenElevenShouldReturnElevenElement() {
        String[] expected = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        SimpleArray<String> result = new SimpleArray<>();
        for (String text : expected) {
            result.add(text);
        }
        int position = 0;
        for (String text : result) {
            assertThat(text.equals(expected[position++]), is(true));
        }
    }

    @Test
    public void whenRemoveTwoElements() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(1);
        array.add(2);
        array.add(10);
        array.add(11);
        array.delete(1);
        array.delete(3);
        assertThat(array.get(0), is(1));
        assertThat(array.get(1), is(10));
        Iterator iter = array.iterator();
        iter.next();
        iter.next();
        assertThat(iter.hasNext(), is(false));
    }

    @Test
    public void whenSetElementThenChange() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(5);
        array.add(2);
        array.add(10);
        array.set(2, 4);
        assertThat(array.get(2), is(4));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenExpectExceptionNoSuchElementException() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("1");
        array.add("2");
        array.add("10");
        Iterator it = array.iterator();
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is("1"));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is("2"));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is("10"));
        assertThat(it.hasNext(), Matchers.is(false));
        it.next();
    }


}