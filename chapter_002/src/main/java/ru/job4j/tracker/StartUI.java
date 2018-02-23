package ru.job4j.tracker;

/**
 * StartUI
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.21
 */
public class StartUI {

    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FINDBYID = "4";
    private static final String FINDBYNAME = "5";
    private static final String EXIT = "6";
    private final Tracker tracker;
    private final Input input;
    private final String separator = System.lineSeparator();

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * main - точка входа.
     *
     * @param args параметры.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }

    /**
     * инициализация программы.
     */
    public void init() {
        //ConsoleInput input = new ConsoleInput();
        while (true) {
            this.showMenu();
            String answer = this.input.ask("Select:");
            if (answer.equals(ADD)) {
                this.addItem();
            } else if (answer.equals(SHOW)) {
                this.showItem();
            } else if (answer.equals(EDIT)) {
                this.editItem();
            } else if (answer.equals(DELETE)) {
                this.deleteItem();
            } else if (answer.equals(FINDBYID)) {
                this.findByID();
            } else if (answer.equals(FINDBYNAME)) {
                this.findByName();
            } else if (answer.equals(EXIT)) {
                break;
            }
            System.out.println(this.separator + this.separator + this.separator);
        }
    }

    /**
     * Поиск заявки по названию.
     */
    private void findByName() {
        String answer = this.input.ask(this.separator + "Введите имя заявки:");
        Item[] result = this.tracker.findByName(answer);
        this.showTask(result);
    }

    /**
     * Поиск заявки по ID.
     */
    private void findByID() {
        String answer = this.input.ask(this.separator + "Введите ID заявки:");
        Item result = this.tracker.findByID(answer);
        this.showTask(result);
    }

    /**
     * Удаление заявки.
     */
    private void deleteItem() {
        String answer = this.input.ask(this.separator + "Введите ID заявки:");
        this.tracker.delete(answer);
    }

    /**
     * Редактирование заявки
     */
    private void editItem() {
        String id = this.input.ask(this.separator + "Введите ID заявки:");
        String name = this.input.ask(this.separator + "Введите новое имя заявки:");
        String desc = this.input.ask(this.separator + "Введите описание заявки:");
        Item item = new Item(name, desc, System.currentTimeMillis());
        item.setID(id);
        this.tracker.replace(id, item);

    }

    /**
     * Добавление заявки
     */
    private void addItem() {
        System.out.println(this.separator + this.separator + "------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание :");
        Item item = new Item(name, desc, System.currentTimeMillis());
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getID() + "-----------");
    }

    /**
     * Отображение всех заявок
     */
    private void showItem() {
        this.showTask(this.tracker.findAll());
    }

    /**
     * Консольное меню
     */
    private void showMenu() {
        System.out.println("------------ Меню: ------------" + this.separator);
        System.out.println("0. Добавить заявку");
        System.out.println("1. Показать все заявки");
        System.out.println("2. Отредактировать заявку");
        System.out.println("3. Удалить заявку");
        System.out.println("4. Найти заявку по ID");
        System.out.println("5. Найти заявку по названию");
        System.out.println("6. Выход");
    }

    /**
     * Отображение списка заявок
     */
    private void showTask(Item[] items) {
        System.out.println(this.separator + this.separator + "------------ Список заявок: --------------" + this.separator + this.separator);
        for (Item item : items) {
            System.out.println("ID: " + item.getID());
            System.out.println("Заявка: " + item.getName());
            System.out.println("Описание: " + item.getDesc());
            System.out.println("Дата создания: " + item.getCreated() + this.separator);
        }
    }

    /**
     * Отображение заявки
     */
    private void showTask(Item item) {
        System.out.println(this.separator + this.separator + "------------ Результат: --------------" + this.separator + this.separator);
        System.out.println("ID: " + item.getID());
        System.out.println("Заявка: " + item.getName());
        System.out.println("Описание: " + item.getDesc());
        System.out.println("Дата создания: " + item.getCreated() + this.separator);
    }
}
