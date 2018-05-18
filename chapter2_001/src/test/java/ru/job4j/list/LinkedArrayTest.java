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

    @Test
    public void whenDeleteAndRemoveTheSameTime() throws InterruptedException {
        LinkedArray<Integer> linked = new LinkedArray<>();
        for (int index = 0; index < 1000; index++) {
            Add a1 = new Add(1, linked);
            Add a2 = new Add(2, linked);
            Add a3 = new Add(3, linked);
            Add a4 = new Add(4, linked);
            Add a5 = new Add(5, linked);
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
                if (index % 2 == 0) {
                    assertThat(linked.removeLast() == null, is(false));
                } else {
                    assertThat(linked.removeFirst() == null, is(false));
                }
            }
        }


    }

    private class Add<E> extends Thread {

        private E element;
        private LinkedArray<E> linkedArray;

        public Add(E element, LinkedArray<E> linkedArray) {
            this.element = element;
            this.linkedArray = linkedArray;
        }

        @Override
        public void run() {
            linkedArray.add(element);
        }
    }
}