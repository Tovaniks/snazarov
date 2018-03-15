package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {

    @Test
    public void whenUserConvertThenConvert() {
        User user1 = new User(1, "Петр", "Москва");
        User user2 = new User(2, "Алексей", "Питер");
        User user3 = new User(3, "Николай", "Ростов");
        User user4 = new User(4, "Андрей", "Новосибирск");
        List<User> input = new ArrayList<>();
        input.add(user1);
        input.add(user2);
        input.add(user3);
        input.add(user4);
        HashMap<Integer, User> result = new UserConvert().process(input);
        assertThat(result.values().toArray(), is(input.toArray()));
    }

}
