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
    void testNoBedsExpectedException() throws NoBedsException {
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

    @Test
    void testNoVentilatorsExpectedException() throws NoBedsException, NoLungVentilatorsException {
        ward.removeAllPatients();
        CovidPatient cp1 = new CovidPatient.CovidPatientBuilder("Gianni", "Mori").
                setRegisteredResidence("Siena").setAge(78).setPositive(true).build();
        ward.addPatient(cp1);
        CovidPatient cp2 = new CovidPatient.CovidPatientBuilder("Bruno", "Bruni").
                setRegisteredResidence("Prato").setAge(45).setPositive(true).build();
        ward.addPatient(cp2);
        CovidPatient cp3 = new CovidPatient.CovidPatientBuilder("Valeria", "Neri").
                setRegisteredResidence("Firenze").setAge(87).setPositive(true).build();
        ward.addPatient(cp3);
        CovidPatient cp4 = new CovidPatient.CovidPatientBuilder("Serena", "Bianchi").
                setRegisteredResidence("Grosseto").setAge(34).setPositive(true).build();
        ward.addPatient(cp4);
        ward.assignVentilator(cp1, LungVentilatorsTypologies.POSITIVE_PRESSURE_VENTILATOR);
        ward.assignVentilator(cp2, LungVentilatorsTypologies.POSITIVE_PRESSURE_VENTILATOR);
        ward.assignVentilator(cp3, LungVentilatorsTypologies.POSITIVE_PRESSURE_VENTILATOR);
        assertThrows(NoLungVentilatorsException.class, () ->  ward.assignVentilator(cp4, LungVentilatorsTypologies.POSITIVE_PRESSURE_VENTILATOR));

        CovidPatient cp5 = new CovidPatient.CovidPatientBuilder("Mauro", "Gori").
                setRegisteredResidence("Massa").setAge(49).setPositive(true).build();
        ward.addPatient(cp5);
        CovidPatient cp6 = new CovidPatient.CovidPatientBuilder("Alberto", "Berti").
                setRegisteredResidence("Lucca").setAge(49).setPositive(true).build();
        ward.addPatient(cp6);
        CovidPatient cp7 = new CovidPatient.CovidPatientBuilder("Giorgia", "Rossi").
                setRegisteredResidence("Siena").setAge(98).setPositive(true).build();
        ward.addPatient(cp7);
        CovidPatient cp8 = new CovidPatient.CovidPatientBuilder("Alberto", "Franchi").
                setRegisteredResidence("Livorno").setAge(92).setPositive(true).build();
        ward.addPatient(cp8);
        ward.assignVentilator(cp5, LungVentilatorsTypologies.NEGATIVE_PRESSURE_VENTILATOR);
        ward.assignVentilator(cp6, LungVentilatorsTypologies.NEGATIVE_PRESSURE_VENTILATOR);
        ward.assignVentilator(cp7, LungVentilatorsTypologies.NEGATIVE_PRESSURE_VENTILATOR);
        assertThrows(NoLungVentilatorsException.class, () -> ward.assignVentilator(cp8, LungVentilatorsTypologies.POSITIVE_PRESSURE_VENTILATOR));
    }
}