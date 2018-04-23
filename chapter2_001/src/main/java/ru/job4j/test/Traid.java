package ru.job4j.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


/**
 * Class Traid.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.23
 */
public class Traid {

    private Map<String, Glass> elements = new HashMap<>();

    /**
     * Добавляем новую таску
     *
     * @param task таска
     * @return успешность операции
     */
    public boolean add(Task task) {
        boolean success;
        if (elements.get(task.getBook()) == null) {
            elements.put(task.getBook(), new Glass(task));
            success = true;
        } else {
            success = elements.get(task.getBook()).insert(task);
        }
        return success;
    }

    /**
     * Выводим на печать содержимое стакана
     *
     * @param book стакан
     */
    public void getGlass(String book) {
        String text = "";
        if (elements.get(book) != null) {
            text = elements.get(book).toString();
        }
        System.out.println(String.format("Sell\tPrice\tBuy%s%s", System.getProperty("line.separator"), text));
    }
}
