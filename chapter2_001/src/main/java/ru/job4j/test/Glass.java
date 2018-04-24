package ru.job4j.test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;


/**
 * Class Glass.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.23
 */
public class Glass {

    private TreeSet<Task> sell = new TreeSet<>();
    private TreeSet<Task> buy = new TreeSet<>();

    /**
     * Конструктор стакана. Добавляем таску
     *
     * @param task таска
     */
    public Glass(Task task) {
        if ("Bid".equals(task.getAction().action())) {
            buy.add(task);
        } else {
            sell.add(task);
        }
    }

    /**
     * Добавляем в стакан таску
     *
     * @param task таска
     * @return true/false успешности добавления
     */
    public boolean insert(Task task) {
        boolean success;
        if ("Delete".equals(task.getType().type())) {
            TreeSet<Task> instance = "Ask".equals(task.getAction().action()) ? sell : buy;
            success = removeTask(task, instance);
        } else {
            success = addTask(task);
        }
        return success;
    }

    /**
     * Добавляем таску
     *
     * @param task таска
     * @return true/false успешности добавления
     */
    private boolean addTask(Task task) {
        boolean success = false;
        if ("Ask".equals(task.getAction().action())) {
            sell(task);
            if (task.getVolume() > 0) {
                success = sell.add(task);
            }
        } else {
            buy(task);
            if (task.getVolume() > 0) {
                success = buy.add(task);
            }
        }
        return success;
    }

    /**
     * Проверяем, нет ли заявок в стакане на продажу по меньшей или равной цене, за которую мы могли бы купить, и покупаем при наличии.
     *
     * @param task заявка
     */
    private void buy(Task task) {
        while (!sell.isEmpty() && sell.last().getPrice() <= task.getPrice() && task.getVolume() > 0) {
            if (sell.last().getVolume() < task.getVolume()) {
                task.setVolume(task.getVolume() - sell.last().getVolume());
                sell.pollLast();
            } else {
                sell.last().setVolume(sell.last().getVolume() - task.getVolume());
                task.setVolume(0);
            }
        }
    }


    /**
     * Проверяем, нет ли заявок в стакане на покупку за большую или равную цену, за которую мы могли бы продать, и продаем при наличии.
     *
     * @param task заявка
     */
    private void sell(Task task) {
        while (!buy.isEmpty() && buy.first().getPrice() >= task.getPrice() && task.getVolume() > 0) {
            if (buy.first().getVolume() < task.getVolume()) {
                task.setVolume(task.getVolume() - buy.first().getVolume());
                buy.pollFirst();
            } else {
                buy.first().setVolume(buy.first().getVolume() - task.getVolume());
                task.setVolume(0);
            }
        }
    }


    /**
     * Удаляем таску из стакана
     *
     * @param task таска
     * @param list список, откуда удаляем таску
     * @return true/false успешности действия
     */
    private boolean removeTask(Task task, TreeSet<Task> list) {
        boolean success = false;
        for (Task elem : list) {
            if (task.getID().equals(elem.getID()) && task.getBook().equals(elem.getBook())) {
                list.remove(elem);
                success = true;
                break;
            }
        }
        return success;
    }


    /**
     * Возвращаем в виде строки содержимое стакана
     *
     * @return содердимое стакана
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        StringBuilder sellTable = new StringBuilder();
        StringBuilder buyTable = new StringBuilder();
        Map<Double, Integer> map;
        map = getMap(sell);
        for (Double key : map.keySet()) {
            sellTable.append(String.format("%s\t%s\t%s", map.get(key), key, System.getProperty("line.separator")));
        }
        map = getMap(buy);
        for (Double key : map.keySet()) {
            sellTable.append(String.format("\t\t%s\t%s%s", key, map.get(key), System.getProperty("line.separator")));
        }
        if ((!sell.isEmpty() && !buy.isEmpty() && buy.first().getPrice() < sell.first().getPrice()) || (!sell.isEmpty() && buy.isEmpty())) {
            result.append(sellTable).append(buyTable);
        } else {
            result.append(buyTable).append(sellTable);
        }
        return result.toString();
    }


    /**
     * Схолопываем содержимое стакана по цене и покупку/продаже
     *
     * @param list список, который будем схлопывать.
     * @return карта со схлопнутыми данными.
     */
    private Map getMap(TreeSet<Task> list) {
        Map<Double, Integer> map = new LinkedHashMap<>();
        for (Task task : list) {
            if (map.get(task.getPrice()) == null) {
                map.put(task.getPrice(), task.getVolume());
            } else {
                map.put(task.getPrice(), map.get(task.getPrice()) + task.getVolume());
            }
        }
        return map;
    }

}

