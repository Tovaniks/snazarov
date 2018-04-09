package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenAddThreeElementsThenAdd() {
        UserStore store = new UserStore();
        User user1 = new User("1");
        User user2 = new User("2");
        User user3 = new User("3");
        store.add(user1);
        store.add(user2);
        store.add(user3);
        assertThat(user1, is(store.findById("1")));
        assertThat(user2, is(store.findById("2")));
        assertThat(user3, is(store.findById("3")));
    }


    @Test
    public void whenDeleteThenNotFind() {
        UserStore store = new UserStore();
        User user1 = new User("1");
        User user2 = new User("2");
        User user3 = new User("3");
        store.add(user1);
        store.add(user2);
        store.add(user3);
        store.delete("2");
        assertThat(null, is(store.findById("2")));
    }

    @Test
    public void whenReplaceThenTrue() {
        UserStore store = new UserStore();
        User user1 = new User("1");
        User user2 = new User("2");
        User user3 = new User("3");
        User newUser = new User("4");
        store.add(user1);
        store.add(user2);
        store.add(user3);
        store.replace("2", newUser);
        assertThat(null, is(store.findById("2")));
        assertThat(newUser, is(store.findById("4")));
    }

}