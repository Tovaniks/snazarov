package ru.job4j.profession;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProfessionTest {
    @Test
    public void whenNameIsAndrey() {
        Profession profession = new Profession();
        profession.setName("Andrey");
        assertThat(profession.getName(), is("Andrey"));
    }

    @Test
    public void whenProfessionIsCarpenter() {
        Profession profession = new Profession();
        profession.setProfession("Столяр");
        assertThat(profession.getProfession(), is("Столяр"));
    }
}
