package ru.job4j.search;

/**
 * User.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.15
 */
public class User {

    private int id;
    private String name;
    private String city;

    /**
     * Конструктор
     *
     * @param id   ID
     * @param name Имя пользователя
     * @param city Город
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Возвращаем ID
     *
     * @return ID
     */
    public int getID() {
        return this.id;
    }

    /**
     * Возвращаем Имя пользователя
     *
     * @return Имя пользователя
     */
    public String getName() {
        return this.name;
    }

    /**
     * Возвращаем Город
     *
     * @return Город
     */
    public String getCity() {
        return this.city;
    }


}