package ru.job4j.search;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Petr", "Arsentev", "534872", "Bryansk"));
        phones.add(new Person("Sergey", "Nazarov", "534500", "Moscow"));
        List<Person> persons = phones.find("Petr");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindBySurname() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Petr", "Arsentev", "534872", "Bryansk"));
        phones.add(new Person("Sergey", "Nazarov", "534500", "Moscow"));
        List<Person> persons = phones.find("ar");
        assertThat(persons.iterator().next().getSurname(), is("Nazarov"));
    }

    @Test
    public void whenFindByAddres() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Petr", "Arsentev", "534872", "Bryansk"));
        phones.add(new Person("Sergey", "Nazarov", "534500", "Moscow"));
        List<Person> persons = phones.find("sc");
        assertThat(persons.iterator().next().getSurname(), is("Nazarov"));
    }

    @Test
    public void whenFindByPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Petr", "Arsentev", "534872", "Bryansk"));
        phones.add(new Person("Sergey", "Nazarov", "534500", "Moscow"));
        List<Person> persons = phones.find("87");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }
}
