package ru.job4j.departments;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepartmentsTest {
    @Test
    public void whenAddOneElementDesc() {
        Departments departments = new Departments();
        departments.addDepartment("K1\\SK1");
        departments.addDepartment("K1\\SK2");
        departments.addDepartment("K1\\SK1\\SSK1");
        departments.addDepartment("K1\\SK1\\SSK2");
        departments.addDepartment("K2");
        departments.addDepartment("K2\\SK1\\SSK1");
        departments.addDepartment("K2\\SK1\\SSK2");
        String[] expected = {"K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"};
        String[] result = departments.getDesc();
        assertThat(expected.length, is(result.length));
        for (int index = 0; index < result.length; index++) {
            assertThat(result[index], is(expected[index]));
        }
    }

    @Test
    public void whenAddOneElementAsc() {
        Departments departments = new Departments();
        departments.addDepartment("K1\\SK1");
        departments.addDepartment("K1\\SK2");
        departments.addDepartment("K1\\SK1\\SSK1");
        departments.addDepartment("K1\\SK1\\SSK2");
        departments.addDepartment("K2");
        departments.addDepartment("K2\\SK1\\SSK1");
        departments.addDepartment("K2\\SK1\\SSK2");
        String[] expected = {"K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] result = departments.getAsc();
        assertThat(expected.length, is(result.length));
        for (int index = 0; index < result.length; index++) {
            assertThat(result[index], is(expected[index]));
        }
    }

    @Test
    public void whenAddAllElementsAsc() {
        Departments departments = new Departments();
        String[] input = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] expected = {"K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        departments.addAllDepartment(input);
        String[] result = departments.getAsc();
        assertThat(expected.length, is(result.length));
        for (int index = 0; index < result.length; index++) {
            assertThat(result[index], is(expected[index]));
        }
    }

    @Test
    public void whenAddAllElementsDesc() {
        Departments departments = new Departments();
        String[] input = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        departments.addAllDepartment(input);
        String[] expected = {"K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"};
        String[] result = departments.getDesc();
        assertThat(expected.length, is(result.length));
        for (int index = 0; index < result.length; index++) {
            assertThat(result[index], is(expected[index]));
        }
    }

    @Test
    public void whenSortAllElementsAsc() {
        Departments departments = new Departments();
        String[] input = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] expected = {"K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] result = departments.getAsc(input);
        assertThat(expected.length, is(result.length));
        for (int index = 0; index < result.length; index++) {
            assertThat(result[index], is(expected[index]));
        }
    }

    @Test
    public void whenSortAllElementsDesc() {
        Departments departments = new Departments();
        String[] input = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] expected = {"K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"};
        String[] result = departments.getDesc(input);
        assertThat(expected.length, is(result.length));
        for (int index = 0; index < result.length; index++) {
            assertThat(result[index], is(expected[index]));
        }
    }
}
