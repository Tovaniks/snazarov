package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final String separator = System.lineSeparator();
    private final String menu = String.format("------------ Меню: ------------%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
            this.separator, this.separator, "0. Добавить заявку", this.separator, "1. Показать все заявки", this.separator,
            "2. Отредактировать заявку", this.separator, "3. Удалить заявку", this.separator, "4. Найти заявку по ID",
            this.separator, "5. Найти заявку по названию", this.separator, "6. Выход", this.separator);


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
        System.out.println(menu);
        assertThat(tracker.findAll(), is(new Item[]{first, third}));
    }

    @Test
    public void whenShowAll() {
        Tracker tracker = new Tracker();
        Item first = new Item("Таска", "Первая моя");
        Item second = new Item("Баг", "второй мой");
        tracker.add(first);
        tracker.add(second);
        String result = initResult(new StubInput(new String[]{"1", "6"}), tracker);

        assertThat(
                result,
                is(String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s", this.separator, this.separator,
                        "------------ Список заявок: --------------", this.separator, this.separator, this.separator, "ID: ", first.getID(),
                        this.separator, "Заявка: ", first.getName(), this.separator, "Описание: ", first.getDesc(), this.separator, "Дата создания: ",
                        first.getCreated(), this.separator, this.separator, "ID: ", second.getID(), this.separator, "Заявка: ", second.getName(),
                        this.separator, "Описание: ", second.getDesc(), this.separator, "Дата создания: ", second.getCreated(), this.separator,
                        this.separator, this.separator, this.separator, this.separator, this.separator)
                ));

    }


    @Test
    public void whenFindByIDThenShow() {
        Tracker tracker = new Tracker();
        Item first = new Item("Таска", "Первая моя");
        Item second = new Item("Баг", "второй мой");
        Item third = new Item("Баг", "третий мой");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        String result = initResult(new StubInput(new String[]{"4", third.getID(), "6"}), tracker);

        assertThat(
                result,
                is(String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s", this.separator, this.separator,
                        "------------ Результат: --------------", this.separator, this.separator, this.separator, "ID: ",
                        third.getID(), this.separator, "Заявка: ", third.getName(), this.separator, "Описание: ", third.getDesc(),
                        this.separator, "Дата создания: ", third.getCreated(), this.separator, this.separator, this.separator,
                        this.separator, this.separator, this.separator)));

    }

    @Test
    public void whenFindByNameThenShow() {
        Tracker tracker = new Tracker();
        Item first = new Item("Таска", "Первая моя");
        Item second = new Item("Баг", "второй мой");
        Item third = new Item("Баг", "третий мой");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        String result = initResult(new StubInput(new String[]{"5", "Баг", "6"}), tracker);
        assertThat(
                result,
                is(
                        String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s", this.separator, this.separator,
                                "------------ Список заявок: --------------", this.separator, this.separator, this.separator, "ID: ", second.getID(),
                                this.separator, "Заявка: ", second.getName(), this.separator, "Описание: ", second.getDesc(), this.separator,
                                "Дата создания: ", second.getCreated(), this.separator, this.separator, "ID: ", third.getID(), this.separator,
                                "Заявка: ", third.getName(), this.separator, "Описание: ", third.getDesc(), this.separator, "Дата создания: ",
                                third.getCreated(), this.separator, this.separator, this.separator, this.separator, this.separator, this.separator)
                ));

    }

    private String initResult(Input input, Tracker tracker) {
        System.setOut(new PrintStream(out));
        new StartUI(input, tracker).init();
        String result = new String(out.toByteArray());
        return result.replaceAll(this.menu, "");
    }


    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }


}
