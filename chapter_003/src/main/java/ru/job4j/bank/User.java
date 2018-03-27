package ru.job4j.bank;

/**
 * User.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.27
 */
public class User implements Comparable<User> {

    private String name;
    private String passport;


    /**
     * Конструктор пользователя
     *
     * @param name     имя
     * @param passport паспорт
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }


    /**
     * Возвращаем имя пользователя
     *
     * @return имя
     */
    public String getName() {
        return this.name;
    }


    /**
     * Возвращаем паспорт пользователя
     *
     * @return паспорт
     */
    public String getPassport() {
        return this.passport;
    }


    /**
     * Компаратор. Сравниваем двух пользователей
     *
     * @param usr второй пользователь
     * @return -1 - текущий пользователь стоит перед usr, 0 - пользователи равны, 1 - текущий пользователь стоит за usr
     */
    @Override
    public int compareTo(User usr) {
        return usr.getName().compareTo(this.name) * -1 == 0 ? usr.getPassport().compareTo(this.getPassport()) * -1 : usr.getName().compareTo(this.name) * -1;
    }

    /**
     * Сравнение двух пользователей через equals
     *
     * @param user второй пользователь
     * @return true/false
     */
    public boolean equals(User user) {
        return this == user || (this.name.equals(user.getName()) && this.passport.equals(user.getPassport()));
    }

    /**
     * Возвращаем hashcode пасспорта пользователя
     *
     * @return hashcode
     */
    public int hashCode() {
        return this.passport.hashCode();
    }
}