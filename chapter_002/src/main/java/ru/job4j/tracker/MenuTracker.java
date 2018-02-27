package ru.job4j.tracker;

/**
 * MenuTracker
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.25
 */
public class MenuTracker {

    private final static String SEPARATOR = System.lineSeparator();
    private final Input input;
    private final Tracker tracker;
    private int positions = 0;
    private UserAction[] actions = new UserAction[7];

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * инициализируем меню
     */
    public void create() {
        addMenuItem(this.new AddItem(this.positions, "Добавить заявку"));
        addMenuItem(new MenuTracker.ShowItem(this.positions, "Показать все заявки"));
        addMenuItem(new EditItem(this.positions, "Отредактировать заявку"));
        addMenuItem(new DeleteItem(this.positions, "Удалить заявку"));
        addMenuItem(new MenuTracker.FindByID(this.positions, "Найти заявку по ID"));
        addMenuItem(new MenuTracker.FindByName(this.positions, "Найти заявку по названию"));
        addMenuItem(this.new Exit(this.positions, "Выход"));
    }

    /**
     * Добавляем пункты меню в меню
     *
     * @param action действие
     */
    private void addMenuItem(UserAction action) {
        if (this.actions.length == this.positions) {
            UserAction[] duplicate = new UserAction[this.actions.length * 2];
            System.arraycopy(this.actions, 0, duplicate, 0, this.actions.length);
            this.actions = duplicate;
        }
        this.actions[this.positions] = action;
        this.positions++;
    }

    /**
     * Выводим меню
     */
    public void showMenu() {
        System.out.println("------------ Меню: ------------" + this.SEPARATOR);
        for (int index = 0; index < this.positions; index++) {
            System.out.println(this.actions[index].info());
        }
    }

    /**
     * Выбираем пункт меню, выполняем необходимые действия и возвращаем признак, нужно ли выходить.
     *
     * @return признак, нужно ли выходить.
     */
    public boolean choose() {
        boolean result = false;
        int[] range = new int[positions];
        for (int i = 0; i < positions; i++) {
            range[i] = i;
        }
        int index = this.input.ask("Select:", range);
        if (this.actions[index].info().equals(String.format("%s. %s", positions - 1, "Выход"))) {
            result = true;
        } else {
            actions[index].execute(this.input, this.tracker);
            System.out.println(this.SEPARATOR + this.SEPARATOR + this.SEPARATOR);
        }
        return result;
    }

    /**
     * AddItem
     *
     * @author Sergey Nazarov
     * @version $Id$
     * @since 2018.02.25
     */
    private class AddItem extends BaseAction {

        /**
         * конструктор
         *
         * @param positions id пункта меню
         * @param name      название пункта меню
         */
        public AddItem(final int positions, final String name) {
            super(positions, name);
        }

        /**
         * Добавляем заявку в трекер
         *
         * @param input   ввод данных.
         * @param tracker хранилище заявок.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println(String.format("%s%s------------ Добавление новой заявки --------------", SEPARATOR, SEPARATOR));
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание :");
            Item item = new Item(name, desc, System.currentTimeMillis());
            tracker.add(item);
            System.out.println(String.format("------------ Новая заявка с getId : %s-----------", item.getID()));
        }
    }

    /**
     * Exit
     *
     * @author Sergey Nazarov
     * @version $Id$
     * @since 2018.02.25
     */
    private class Exit extends BaseAction {

        /**
         * конструктор
         *
         * @param positions id пункта меню
         * @param name      название пункта меню
         */
        public Exit(int positions, String name) {
            super(positions, name);
        }

        /**
         * Выходим из программы. Ничего не делаем.
         *
         * @param input   ввод данных.
         * @param tracker хранилище заявок.
         */
        @Override
        public void execute(Input input, Tracker tracker) {

        }
    }

    /**
     * ShowItem
     *
     * @author Sergey Nazarov
     * @version $Id$
     * @since 2018.02.25
     */
    private static class ShowItem extends BaseAction {

        /**
         * конструктор
         *
         * @param positions id пункта меню
         * @param name      название пункта меню
         */
        public ShowItem(int positions, String name) {
            super(positions, name);
        }

        /**
         * Показываем все заявке в трекере
         *
         * @param input   ввод данных.
         * @param tracker хранилище заявок.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            new ShowTasks().showTask(tracker.findAll());
        }
    }

    /**
     * FindByName
     *
     * @author Sergey Nazarov
     * @version $Id$
     * @since 2018.02.25
     */
    private static class FindByName extends BaseAction {

        /**
         * конструктор
         *
         * @param positions id пункта меню
         * @param name      название пункта меню
         */
        public FindByName(int positions, String name) {
            super(positions, name);
        }

        /**
         * Ищем заявки по имени
         *
         * @param input   ввод данных.
         * @param tracker хранилище заявок.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String answer = input.ask(SEPARATOR + "Введите имя заявки:");
            Item[] result = tracker.findByName(answer);
            new ShowTasks().showTask(result);
        }
    }

    /**
     * FindByID
     *
     * @author Sergey Nazarov
     * @version $Id$
     * @since 2018.02.25
     */
    private static class FindByID extends BaseAction {

        /**
         * конструктор
         *
         * @param positions id пункта меню
         * @param name      название пункта меню
         */
        public FindByID(int positions, String name) {
            super(positions, name);
        }

        /**
         * Ищем заявку по ID
         *
         * @param input   ввод данных.
         * @param tracker хранилище заявок.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String answer = input.ask(SEPARATOR + "Введите ID заявки:");
            Item result = tracker.findByID(answer);
            new ShowTasks().showTask(result);
        }
    }

    /**
     * ShowTasks
     *
     * @author Sergey Nazarov
     * @version $Id$
     * @since 2018.02.25
     */
    private static class ShowTasks {

        /**
         * Выводим заявки списком
         *
         * @param items список заявок.
         */
        private void showTask(Item[] items) {
            System.out.println(SEPARATOR + SEPARATOR + "------------ Список заявок: --------------" + SEPARATOR + SEPARATOR);
            for (Item item : items) {
                showItemInfo(item);
            }
        }

        /**
         * Выводим одну заявку
         *
         * @param item заявка.
         */
        private void showTask(Item item) {
            System.out.println(SEPARATOR + SEPARATOR + "------------ Результат: --------------" + SEPARATOR + SEPARATOR);
            showItemInfo(item);
        }

        /**
         * Выводим данные по заявке(ID, название, описание, дату создания)
         *
         * @param item заявка.
         */
        private void showItemInfo(Item item) {
            System.out.println("ID: " + item.getID());
            System.out.println("Заявка: " + item.getName());
            System.out.println("Описание: " + item.getDesc());
            System.out.println("Дата создания: " + item.getCreated() + SEPARATOR);
        }
    }
}

/**
 * DeleteItem
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.25
 */
class DeleteItem extends BaseAction {

    private final String separator = System.lineSeparator();

    /**
     * конструктор
     *
     * @param positions id пункта меню
     * @param name      название пункта меню
     */
    public DeleteItem(int positions, String name) {
        super(positions, name);
    }

    /**
     * Удаляем заявку по ID
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        String answer = input.ask(this.separator + "Введите ID заявки:");
        tracker.delete(answer);
    }
}

/**
 * EditItem
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.25
 */
class EditItem extends BaseAction {

    private final String separator = System.lineSeparator();

    /**
     * конструктор
     *
     * @param positions id пункта меню
     * @param name      название пункта меню
     */
    public EditItem(int positions, String name) {
        super(positions, name);
    }

    /**
     * Редактируем заявку
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    @Override
    public void execute(Input input, Tracker tracker) {

        String id = input.ask(this.separator + "Введите ID заявки:");
        String name = input.ask(this.separator + "Введите новое имя заявки:");
        String desc = input.ask(this.separator + "Введите описание заявки:");
        Item item = new Item(name, desc, System.currentTimeMillis());
        item.setID(id);
        tracker.replace(id, item);
    }
}