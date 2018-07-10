package ru.job4j.tracker;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    private static final Logger LOG = LogManager.getLogger(TrackerTest.class);


    @Test
    public void whenReplaceNameThenReturnNewName() {
        try (Tracker tracker = new Tracker(new Config())) {
            Item previous = new Item("test1", "testDescription");
            tracker.add(previous);
            Item next = new Item("test2", "testDescription2");
            next.setID(previous.getID());
            tracker.replace(previous.getID(), next);
            assertThat(tracker.findByID(previous.getID()).getName(), is("test2"));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }


    @Test
    public void whenAddNewItem() {
        try (Tracker tracker = new Tracker(new Config())) {
            Item firstItem = new Item("task", "some text");
            Item secondItem = new Item("bug", "text");
            tracker.add(firstItem);
            tracker.add(secondItem);
            Item[] result = tracker.findAll();
            Item[] expect = new Item[]{firstItem, secondItem};
            for (int index = 0; index < result.length; index++) {
                assertThat(result[index].equals(expect[index]), is(true));
            }
            assertThat(result.length, is(expect.length));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

    }


    @Test
    public void whenTrackerIsEmpty() {
        try (Tracker tracker = new Tracker(new Config())) {
            assertThat(tracker.findAll(), is(new Item[]{}));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }


    @Test
    public void whenElevenItems() {
        try (Tracker tracker = new Tracker(new Config())) {
            Item[] massive = new Item[11];
            for (int index = 0; index < 11; index++) {
                massive[index] = new Item("task", "some text");
                tracker.add(massive[index]);
            }
            assertThat(tracker.findAll(), is(massive));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }


    @Test
    public void whenFindByName() {
        try (Tracker tracker = new Tracker(new Config())) {
            Item[] expect = new Item[]{new Item("task", "какой-то текст"),
                    new Item("task", "еще какой-то текст"),
                    new Item("task", "и тут текст"),
                    new Item("task", "и здесь текст")};
            tracker.add(new Item("баг", "текст"));
            tracker.add(expect[0]);
            tracker.add(new Item("поручение", "рандомный текст"));
            tracker.add(expect[1]);
            tracker.add(expect[2]);
            tracker.add(new Item("фича", "тут что-то должно быть"));
            tracker.add(new Item("фича", "фича"));
            tracker.add(expect[3]);
            tracker.add(new Item("таска", "фича"));
            assertThat(tracker.findByName("task"), is(expect));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

    }

    @Test
    public void whenNotFindByName() {
        try (Tracker tracker = new Tracker(new Config())) {
            tracker.add(new Item("баг", "текст"));
            tracker.add(new Item("поручение", "рандомный текст"));
            tracker.add(new Item("фича", "тут что-то должно быть"));
            assertThat(tracker.findByName("task"), is(new Item[]{}));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }


    }

    @Test
    public void whenDeleteItemInMassive() {
        try (Tracker tracker = new Tracker(new Config())) {
            Item first = new Item("баг", "текст");
            Item second = new Item("поручение", "рандомный текст");
            Item third = new Item("фича", "тут что-то должно быть");
            tracker.add(first);
            tracker.add(second);
            tracker.add(third);
            tracker.delete(second.getID());
            assertThat(tracker.findAll(), is(new Item[]{first, third}));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void whenFindItemByID() {
        try (Tracker tracker = new Tracker(new Config())) {
            Item first = new Item("баг", "текст");
            Item second = new Item("поручение", "рандомный текст");
            Item third = new Item("фича", "тут что-то должно быть");
            tracker.add(first);
            tracker.add(second);
            tracker.add(third);
            Item result = tracker.findByID(second.getID());
            assertThat(result, is(second));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Before
    public void removeAll() {
        Tracker tracker = new Tracker(new Config());
        for (Item item : tracker.findAll()) {
            tracker.delete(item.getID());
        }
    }
}

