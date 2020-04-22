package com.covid.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CovidWardTest {
    CovidWard ward;

    @BeforeEach
    void setUp() {
        ward = CovidWard.getInstance(new Doctor("Giovanni", "Neri", "pneumologia"));
    }

    @Test
    void getInstance() {
        assertSame(ward, CovidWard.getInstance(new Doctor("Giacomo", "Bianchi", "cardiologia")));
    }

    @Test
    void getDirector() {
        assertEquals(ward.getDirector().getName(), "Giovanni");
        assertEquals(ward.getDirector().getSurname(), "Neri");
        assertEquals(ward.getDirector().getSpecialization(), "pneumologia");
    }

    @Test
    void getOccupiedBeds() throws NoBedsException {
        ward.removeAllPatients();
        ward.addPatient(new CovidPatient.CovidPatientBuilder("Gianni", "Mori").
                setRegisteredResidence("Siena").setAge(78).setPositive(true).build());
        assertEquals(ward.getOccupiedBeds(), 1);
    }

    @Test
    void addPatient() throws NoBedsException {
        ward.removeAllPatients();
        ward.addPatient(new CovidPatient.CovidPatientBuilder("Gianni", "Mori").
                setRegisteredResidence("Siena").setAge(78).setPositive(true).build());
        assertEquals(ward.getPatients().get(0).getName(), "Gianni");
        assertEquals(ward.getPatients().get(0).getSurname(), "Mori");
        assertEquals(ward.getPatients().get(0).getRegisteredResidence(), "Siena");
        assertEquals(ward.getPatients().get(0).getAge(), 78);
        assertTrue(ward.getPatients().get(0).isPositive());
    }

    @Test
    void testExpectedException() throws NoBedsException {
        ward.removeAllPatients();
        ward.addPatient(new CovidPatient.CovidPatientBuilder("Gianni", "Mori").
                setRegisteredResidence("Siena").setAge(78).setPositive(true).build());
        ward.addPatient(new CovidPatient.CovidPatientBuilder("Bruno", "Bruni").
                setRegisteredResidence("Prato").setAge(45).setPositive(true).build());
        ward.addPatient(new CovidPatient.CovidPatientBuilder("Valeria", "Neri").
                setRegisteredResidence("Firenze").setAge(87).setPositive(true).build());
        ward.addPatient(new CovidPatient.CovidPatientBuilder("Serena", "Bianchi").
                setRegisteredResidence("Grosseto").setAge(34).setPositive(true).build());
        ward.addPatient(new CovidPatient.CovidPatientBuilder("Mauro", "Gori").
                setRegisteredResidence("Massa").setAge(49).setPositive(true).build());
        ward.addPatient(new CovidPatient.CovidPatientBuilder("Alberto", "Berti").
                setRegisteredResidence("Lucca").setAge(49).setPositive(true).build());
        ward.addPatient(new CovidPatient.CovidPatientBuilder("Giorgia", "Rossi").
                setRegisteredResidence("Siena").setAge(98).setPositive(true).build());
        ward.addPatient(new CovidPatient.CovidPatientBuilder("Alberto", "Franchi").
                setRegisteredResidence("Livorno").setAge(92).setPositive(true).build());
        ward.addPatient(new CovidPatient.CovidPatientBuilder("Mario", "Innocenti").
                setRegisteredResidence("Pistoia").setAge(62).setPositive(true).build());
        ward.addPatient(new CovidPatient.CovidPatientBuilder("Paola", "Neri").
                setRegisteredResidence("Firenze").setAge(57).setPositive(true).build());
        assertThrows(NoBedsException.class, () -> ward.addPatient(new CovidPatient.CovidPatientBuilder("Luisa", "Mori").
                setRegisteredResidence("Prato").setAge(82).setPositive(true).build()));
    }
}