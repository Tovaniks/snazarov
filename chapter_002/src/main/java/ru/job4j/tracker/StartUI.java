package ru.job4j.tracker;

/**
 * StartUI
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.21
 */
public class StartUI {

    private final Tracker tracker;
    private final Input input;

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
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.create();
        do {
            menu.showMenu();
        }
        while (!menu.choose());
    }
}
