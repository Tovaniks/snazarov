package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {


    @Test
    public void whenUserAddItemsThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        String name = "test name";
        String description = "desc";
        Item item = tracker.add(new Item(name, description));
        Input input = new StubInput(new String[]{"2", item.getID(), "new test", "new desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByID(item.getID()).getName(), is("new test"));
    }

    @Test
    public void whenUserDeleteItemInTrackerThenItemBecomeLess() {
        Tracker tracker = new Tracker();
        Item first = new Item("Таска", "Первая моя");
        Item second = new Item("Баг", "второй мой");
        Item third = new Item("Баг", "третий мой");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        Input input = new StubInput(new String[]{"3", second.getID(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll(), is(new Item[]{first, third}));
    }
}
