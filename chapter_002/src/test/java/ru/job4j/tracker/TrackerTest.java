package ru.job4j.tracker;

import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {


    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setID(previous.getID());
        tracker.replace(previous.getID(), next);
        assertThat(tracker.findByID(previous.getID()).getName(), is("test2"));
    }


    @Test
    public void whenAddNewItem() {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("task", "some text", 555L);
        Item secondItem = new Item("bug", "text", 550L);
        tracker.add(firstItem);
        tracker.add(secondItem);
        assertThat(tracker.findAll(), is(new Item[]{firstItem, secondItem}));
    }


    @Test
    public void whenTrackerIsEmpty() {
        Tracker tracker = new Tracker();
        assertThat(tracker.findAll(), is(new Item[]{}));
    }


    @Test
    public void whenElevenItems() {
        Tracker tracker = new Tracker();
        Item[] massive = new Item[11];
        for (int index = 0; index < 11; index++) {
            massive[index] = new Item("task", "some text", 555L);
            tracker.add(massive[index]);
        }
        assertThat(tracker.findAll(), is(massive));
    }


    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        Item[] expect = new Item[]{new Item("task", "какой-то текст", 555L),
                new Item("task", "еще какой-то текст", 10L),
                new Item("task", "и тут текст", 35L),
                new Item("task", "и здесь текст", 26L)};
        tracker.add(new Item("баг", "текст", 55L));
        tracker.add(expect[0]);
        tracker.add(new Item("поручение", "рандомный текст", 100L));
        tracker.add(expect[1]);
        tracker.add(expect[2]);
        tracker.add(new Item("фича", "тут что-то должно быть", 123L));
        tracker.add(new Item("фича", "фича", 5L));
        tracker.add(expect[3]);
        tracker.add(new Item("таска", "фича", 1L));
        assertThat(tracker.findByName("task"), is(expect));


    }

    @Test
    public void whenNotFindByName() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("баг", "текст", 55L));
        tracker.add(new Item("поручение", "рандомный текст", 100L));
        tracker.add(new Item("фича", "тут что-то должно быть", 123L));
        assertThat(tracker.findByName("task"), is(new Item[]{}));


    }

    @Test
    public void whenDeleteItemInMassive() {
        Tracker tracker = new Tracker();
        Item first = new Item("баг", "текст", 55L);
        Item second = new Item("поручение", "рандомный текст", 100L);
        Item third = new Item("фича", "тут что-то должно быть", 123L);
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        tracker.delete(second.getID());
        assertThat(tracker.findAll(), is(new Item[]{first, third}));
    }

    @Test
    public void whenFindItemByID() {
        Tracker tracker = new Tracker();
        Item first = new Item("баг", "текст", 55L);
        Item second = new Item("поручение", "рандомный текст", 100L);
        Item third = new Item("фича", "тут что-то должно быть", 123L);
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        Item result = tracker.findByID(second.getID());
        assertThat(result, is(second));
    }
}

