package ru.job4j.search;

/**
 * Person.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.14
 */
public class Person {
    private String name;
    private String surname;
    private String phone;
    private String address;

    /**
     * Конструктор
     *
     * @param name    Имя
     * @param surname Фамилия
     * @param phone   Телефон
     * @param address Адресс
     */
    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Получаем имя
     *
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Получаем фамилию
     *
     * @return фамилия
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Получаем телефон
     *
     * @return телефон
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Получаем адрес
     *
     * @return адрес
     */
    public String getAddress() {
        return address;
    }
}
