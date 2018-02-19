package ru.job4j.profession;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DoctorTest {
    @Test
    public void whenDoctorHeal() {
        Doctor doctor = new Doctor();
        doctor.setName("Иван Васильевич");
        doctor.setProfession("Доктор");
        Patient patient = new Patient();
        patient.setName("Виктор");
        assertThat(doctor.heal(patient), is("Доктор Иван Васильевич лечит Виктор"));
    }
}
