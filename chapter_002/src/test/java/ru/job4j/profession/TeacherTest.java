package ru.job4j.profession;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TeacherTest {
    @Test
    public void whenTeacherTeaching() {
        Teacher teacher = new Teacher();
        teacher.setName("Петр Сергеевич");
        teacher.setProfession("Профессор");
        Studient studient = new Studient();
        studient.setName("Андрей");
        assertThat(teacher.teach(studient), is("Профессор Петр Сергеевич учит Андрей"));
    }
}
