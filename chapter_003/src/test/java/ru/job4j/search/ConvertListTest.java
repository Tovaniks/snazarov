package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ConvertListTest {
    @Test
    public void whenConvertArrayToList() {
        ConvertList converter = new ConvertList();
        int[][] expected = new int[][]{{0, 1, 1}, {0, 2}, {3, 4, 2}};
        List<Integer> result = converter.toList(expected);
        int index = 0;
        for (int[] anExpected : expected) {
            for (int anAnExpected : anExpected) {
                assertThat(result.toArray()[index], is(anAnExpected));
                index++;
            }
        }
    }

    @Test
    public void whenConvertListToArrayThreeOnFour() {
        ConvertList converter = new ConvertList();
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(0);
        input.add(10);
        input.add(23);
        input.add(1);
        input.add(4);
        input.add(10);
        int[][] result = converter.toArray(input, 3);
        int[][] expected = {{1, 2, 3}, {4, 0, 10}, {23, 1, 4}, {10, 0, 0}};
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                assertThat(result[i][j], is(expected[i][j]));
            }
        }
    }

    @Test
    public void whenConvertListOfArrayToListOfInteger() {
        ConvertList converter = new ConvertList();
        List<int[]> input = new ArrayList<>();
        input.add(new int[]{1, 2});
        input.add(new int[]{3, 4, 5, 6});
        List<Integer> result = converter.convert(input);
        int[] expected = {1, 2, 3, 4, 5, 6};
        for (int index = 0; index < result.size(); index++) {
            assertThat(result.get(index), is(expected[index]));
        }
    }
}
