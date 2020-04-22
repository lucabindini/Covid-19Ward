package com.covid.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {
    Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor("Giovanni", "Neri", "pneumologia");
    }

    @Test
    void getName() {
        assertEquals(doctor.getName(), "Giovanni");
    }

    @Test
    void getSurname() {
        assertEquals(doctor.getSurname(), "Neri");

    }

    @Test
    void getSpecialization() {
        assertEquals(doctor.getSpecialization(), "pneumologia");

    }
}