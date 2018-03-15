package ru.job4j.sort;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SortUserTest {

    @Test
    public void whenSortForUsers() {
        User user1 = new User("Сергей", 21);
        User user2 = new User("Андрей", 24);
        User user3 = new User("Петр", 23);
        User user4 = new User("Сергей", 25);
        List<User> input = new ArrayList<>();
        input.add(user1);
        input.add(user2);
        input.add(user3);
        input.add(user4);
        List<User> expected = new ArrayList<>();
        expected.add(user2);
        expected.add(user3);
        expected.add(user1);
        expected.add(user4);
        Set<User> result = new SortUser().sort(input);
        for (int index = 0; index < result.size(); index++) {
            assertThat(expected.get(index), is(result.toArray()[index]));
        }
    }

    @Test
    public void whenSortByNameLengh() {
        User user1 = new User("Сергей", 21);
        User user2 = new User("Антон", 24);
        User user3 = new User("Петр", 23);
        User user4 = new User("Ярослав", 25);
        List<User> input = new ArrayList<>();
        input.add(user1);
        input.add(user2);
        input.add(user3);
        input.add(user4);
        List<User> expected = new ArrayList<>();
        expected.add(user3);
        expected.add(user2);
        expected.add(user1);
        expected.add(user4);
        List<User> result = new SortUser().sortNameLength(input);
        for (int index = 0; index < result.size(); index++) {
            assertThat(expected.get(index), is(result.toArray()[index]));
        }
    }

    @Test
    public void whenSortByAllFields() {
        User user1 = new User("Сергей", 21);
        User user2 = new User("Андрей", 24);
        User user3 = new User("Петр", 23);
        User user4 = new User("Сергей", 25);
        List<User> input = new ArrayList<>();
        input.add(user1);
        input.add(user2);
        input.add(user3);
        input.add(user4);
        List<User> expected = new ArrayList<>();
        expected.add(user2);
        expected.add(user3);
        expected.add(user1);
        expected.add(user4);
        List<User> result = new SortUser().sortByAllFields(input);
        for (int index = 0; index < result.size(); index++) {
            assertThat(expected.get(index), is(result.toArray()[index]));
        }
    }
}
