package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tracker
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.21
 */

public class Tracker {
    private List<Item> items = new ArrayList<>();
    private static final Random RANDOM = new Random();


    /**
     * Добавляем элемент в трекер
     *
     * @param item таска
     * @return добавленное значение.
     */
    public Item add(Item item) {
        items.add(item);
        item.setID(generateID());
        return item;
    }


    /**
     * Меняем элемент в трекере по ID
     *
     * @param id   id таски
     * @param item таска
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < this.items.size(); index++) {
            if (this.items.get(index).getID().equals(id)) {
                this.items.set(index, item);
                break;
            }
        }
    }


    /**
     * Удаляем элемент по уникальной ID
     *
     * @param id уникальное ID
     */
    public void delete(String id) {
        for (int index = 0; index < this.items.size(); index++) {
            if (this.items.get(index).getID().equals(id)) {
                this.items.remove(index);
                break;
            }
        }
    }


    /**
     * Возвращаем список тасок
     *
     * @return список тасок.
     */
    public Item[] findAll() {
        Item[] result = new Item[items.size()];
        System.arraycopy(this.items.toArray(), 0, result, 0, items.size());
        return result;
    }

    /**
     * Ищем все таски по указанному имени
     *
     * @param key название таски
     * @return список тасок с одинаковым названием.
     */
    public Item[] findByName(String key) {
        Tracker result = new Tracker();
        for (Item item : this.items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result.findAll();
    }

    /**
     * Ищем таску по уникальному ID
     *
     * @param id уникальное id
     * @return возвращаем элемент списка или Null, если не нашли
     */
    public Item findByID(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item.getID().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }


    /**
     * Генерация уникального ключа
     *
     * @return уникальный ключ.
     */
    private String generateID() {
        return String.valueOf(System.currentTimeMillis() + RANDOM.nextInt());
    }

}
