package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BinaryTreeSearchTest {

    @Test
    public void whenAddDuplicateElementsThenFalse() {
        BinaryTreeSearch<Integer> bts = new BinaryTreeSearch<>();
        assertThat(bts.add(100), is(true));
        assertThat(bts.add(100), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator() {
        BinaryTreeSearch<Integer> bts = new BinaryTreeSearch<>();
        assertThat(bts.add(100), is(true));
        assertThat(bts.add(13), is(true));
        assertThat(bts.add(24), is(true));
        assertThat(bts.add(5), is(true));
        assertThat(bts.add(2), is(true));
        assertThat(bts.add(23), is(true));
        assertThat(bts.add(43), is(true));
        assertThat(bts.add(1), is(true));
        assertThat(bts.add(0), is(true));
        assertThat(bts.add(7), is(true));
        Iterator iterator = bts.iterator();
        assertThat(iterator.next(), is(0));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(7));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(13));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(23));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(24));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(43));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(100));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }
}