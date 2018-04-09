package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddThreeElementsThenAdd() {
        RoleStore store = new RoleStore();
        Role role1 = new Role("1");
        Role role2 = new Role("2");
        Role role3 = new Role("3");
        store.add(role1);
        store.add(role2);
        store.add(role3);
        assertThat(role1, is(store.findById("1")));
        assertThat(role2, is(store.findById("2")));
        assertThat(role3, is(store.findById("3")));
    }


    @Test
    public void whenDeleteThenNotFind() {
        RoleStore store = new RoleStore();
        Role role1 = new Role("1");
        Role role2 = new Role("2");
        Role role3 = new Role("3");
        store.add(role1);
        store.add(role2);
        store.add(role3);
        store.delete("2");
        assertThat(null, is(store.findById("2")));
    }

    @Test
    public void whenReplaceThenTrue() {
        RoleStore store = new RoleStore();
        Role role1 = new Role("1");
        Role role2 = new Role("2");
        Role role3 = new Role("3");
        Role newRole = new Role("4");
        store.add(role1);
        store.add(role2);
        store.add(role3);
        store.replace("2", newRole);
        assertThat(null, is(store.findById("2")));
        assertThat(newRole, is(store.findById("4")));
    }
}