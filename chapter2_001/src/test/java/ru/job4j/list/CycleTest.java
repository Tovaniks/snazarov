package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CycleTest {

    @Test
    public void whenWeHaveCycle() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertThat(new Cycle().hasCycle(first), is(true));
    }

    @Test
    public void whenWeHaveCycleInMiddle() {
        Node<Integer> first = new Node<>(2);
        Node<Integer> two = new Node<>(3);
        Node<Integer> third = new Node<>(4);
        Node<Integer> four = new Node<>(5);
        first.next = two;
        two.next = third;
        third.next = two;
        four.next = first;
        assertThat(new Cycle().hasCycle(first), is(true));
    }

    @Test
    public void whenWeDontHaveCycle() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        first.next = two;
        two.next = third;
        third.next = four;
        assertThat(new Cycle().hasCycle(first), is(false));

    }

}