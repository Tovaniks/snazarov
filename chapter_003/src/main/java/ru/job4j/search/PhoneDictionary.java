package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * PhoneDictionary.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.14
 */
public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();


    /**
     * Добавляем человека справочник
     *
     * @param person человек
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Ищем пользователя по заданному ключу
     *
     * @param key ключ
     * @return пользователь
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (person.toString().contains(key)) {
                result.add(person);
            }
        }
        return result;
    }
}
