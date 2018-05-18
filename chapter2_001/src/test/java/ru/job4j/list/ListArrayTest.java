package ru.job4j.list;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ListArrayTest {

    @Test(expected = NoSuchElementException.class)
    public void whenAddThreeElementsThenGetTreeElements() {
        ListArray<String> list = new ListArray<>();
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
        ListArray<Integer> list = new ListArray<>();
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
        ListArray<String> list = new ListArray<>();
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


    @Test
    public void whenDeleteAndRemoveTheSameTime() throws InterruptedException {
        ListArray<Integer> list = new ListArray<>();
        List<Integer> numbers = new ArrayList<>();
        for (int index = 0; index < 500; index++) {
            numbers.add(1);
            numbers.add(2);
            numbers.add(3);
            numbers.add(4);
            numbers.add(5);
            Add a1 = new Add(1, list);
            Add a2 = new Add(2, list);
            Add a3 = new Add(3, list);
            Add a4 = new Add(4, list);
            Add a5 = new Add(5, list);
            a1.start();
            a2.start();
            a3.start();
            a4.start();
            a5.start();
            a1.join();
            a2.join();
            a3.join();
            a4.join();
            a5.join();
            for (int i = 0; i < 5; i++) {
                assertThat(list.get(i) == null, is(false));
                assertThat(numbers.remove(list.get(i)), is(true));
            }
        }
    }

    private class Add<E> extends Thread {

        private E element;
        private ListArray<E> list;

        public Add(E element, ListArray<E> list) {
            this.element = element;
            this.list = list;
        }

        @Override
        public void run() {
            list.add(element);
        }
    }
}