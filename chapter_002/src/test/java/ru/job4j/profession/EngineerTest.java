package ru.job4j.profession;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EngineerTest {
    @Test
    public void whenEngineerBuilding() {
        Engineer engineer= new Engineer();
        engineer.setName("Евгения");
        engineer.setProfession("Прораб");
        House house = new House();
        house.setType("Торговый Центр");
        assertThat(engineer.build(house), is("Прораб Евгения строит Торговый Центр"));
    }
}
