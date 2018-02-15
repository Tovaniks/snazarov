package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenArrayWithDuplicate() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] result = arrayDuplicate.remove(new String[]{"Привет", "Мир", "Привет", "Супер", "Мир"});
        assertThat(result, is(new String[]{"Привет", "Мир", "Супер"}));
    }


    @Test
    public void whenArrayWithoutDuplicate() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] result = arrayDuplicate.remove(new String[]{"привет", "пока", "один", "две"});
        assertThat(result, is(new String[]{"привет", "пока", "один", "две"}));
    }

}
