package ru.job4j.test;



import java.util.HashMap;
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

    private Map<Action, TreeSet<Task>> traiding = new HashMap<>();

    /**
     * Конструктор стакана. Добавляем таску
     *
     * @param task таска
     */
    public Glass(Task task) {
        init();
        traiding.get(task.getAction()).add(task);
    }

    /**
     * Инициализация карты продажы/покупки
     */
    private void init() {
        traiding.put(Action.BID, new TreeSet<>());
        traiding.put(Action.ASK, new TreeSet<>());
    }

    /**
     * Добавляем в стакан таску
     *
     * @param task таска
     * @return true/false успешности добавления
     */
    public boolean insert(Task task) {
        return Type.DELETE == task.getType() ? removeTask(task, traiding.get(task.getAction())) : addTask(task);
    }

    /**
     * Добавляем таску
     *
     * @param task таска
     * @return true/false успешности добавления
     */
    private boolean addTask(Task task) {
        boolean changed;
        boolean added = false;
        Integer before = task.getVolume();
        if (Action.ASK == task.getAction()) {
            sell(task);
        } else {
            buy(task);
        }
        changed = !before.equals(task.getVolume());
        if (task.getVolume() > 0) {
            added = traiding.get(task.getAction()).add(task);
        }
        return added || changed;
    }

    /**
     * Проверяем, нет ли заявок в стакане на продажу по меньшей или равной цене, за которую мы могли бы купить, и покупаем при наличии.
     *
     * @param task заявка
     */
    private void buy(Task task) {
        TreeSet<Task> sell = traiding.get(Action.ASK);
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
        TreeSet<Task> buy = traiding.get(Action.BID);
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
        TreeSet<Task> sell = traiding.get(Action.ASK);
        TreeSet<Task> buy = traiding.get(Action.BID);
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

