package ru.job4j.test2;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class WorldIndexTest {

    @Test
    public void whenWordIsNoFindThenNull() {
        WorldIndex worldIndex = new WorldIndex();
        worldIndex.loadFile("в голове моей опилки, не беда");
        assertThat(worldIndex.getIndexes4Word("a") == null, is(true));
    }

    @Test
    public void whenWordIsNoFindThenSet() {
        WorldIndex worldIndex = new WorldIndex();
        worldIndex.loadFile("в голове в моей опилки, не беда");
        TreeSet<Integer> expected = new TreeSet<>();
        Set<Integer> result = worldIndex.getIndexes4Word("в");
        expected.add(0);
        expected.add(2);
        assertThat(expected.containsAll(result), is(true));
    }

}