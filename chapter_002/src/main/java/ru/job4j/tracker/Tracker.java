package ru.job4j.tracker;

import java.util.Random;

/**
 * Tracker
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.21
 */

public class Tracker {
    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random RANDOM = new Random();


    /**
     * Добавляем элемент в трекер
     *
     * @param item таска
     * @return добавленное значение.
     */
    public Item add(Item item) {
        if (this.position == this.items.length) {
            Item[] array = new Item[this.items.length * 2];
            System.arraycopy(this.items, 0, array, 0, this.items.length);
            this.items = array;
        }
        item.setID(generateID());
        this.items[this.position++] = item;
        return item;
    }


    /**
     * Меняем элемент в трекере по ID
     *
     * @param id   id таски
     * @param item таска
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getID().equals(id)) {
                this.items[index] = item;
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
        for (int index = 0; index < position; index++) {
            if (this.items[index].getID().equals(id)) {
                System.arraycopy(this.items, index + 1, this.items, index, this.position - index - 1);
                this.position--;
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
        Item[] result = new Item[this.position];
        System.arraycopy(this.items, 0, result, 0, this.position);
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
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getName().equals(key)) {
                result.add(this.items[index]);
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
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getID().equals(id)) {
                result = items[index];
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
