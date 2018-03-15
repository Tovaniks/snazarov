package ru.job4j.sort;


/**
 * User
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.25
 */
public class User implements Comparable<User> {

    private int age;
    private String name;

    /**
     * Конструктор пользователя
     *
     * @param name имя
     * @param age  возраст
     */
    public User(String name, int age) {
        this.age = age;
        this.name = name;
    }


    /**
     * Получаем возраст
     *
     * @return возраст
     */
    public int getAge() {
        return this.age;
    }


    /**
     * Получаем имя пользователя
     *
     * @return имя
     */
    public String getName() {
        return this.name;
    }


    /**
     * Компаратор. Сравниваем двух пользователей
     *
     * @param usr второй пользователь
     * @return -1 - текущий пользователь стоит перед usr, 0 - пользователи равны, 1 - текущий пользователь стоит за usr
     */
    @Override
    public int compareTo(User usr) {
        int result = usr.getName().compareTo(this.name) * -1;
        if (result == 0) {
            result = Integer.compare(this.age, usr.age);
        }
        return result;
    }
}