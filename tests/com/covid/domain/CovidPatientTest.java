package com.covid.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CovidPatientTest {
    CovidPatient patient;

    @BeforeEach
    void setUp() {
        patient = new CovidPatient.CovidPatientBuilder("Mario", "Rossi").
                setRegisteredResidence("Firenze").setAge(75).setPositive(true).build();
    }

    @Test
    void getName() {
        assertEquals(patient.getName(), "Mario");
    }

    @Test
    void getSurname() {
        assertEquals(patient.getSurname(), "Rossi");
    }

    @Test
    void getRegisteredResidence() {
        assertEquals(patient.getRegisteredResidence(), "Firenze");
    }

    @Test
    void getAge() {
        assertEquals(patient.getAge(), 75);
    }

    @Test
    void isPositive() {
        assertTrue(patient.isPositive());
    }

    @Test
    void setPositive() {
        patient.setPositive(false);
        assertFalse(patient.isPositive());
    }

    @Test
    void addPreviousPathology() throws MaxPreviousPathologiesException {
        patient.getPreviousPathologies().clear();
        patient.addPreviousPathology(new Pathology("Problemi Cardiovascolari", ""));
        assertEquals(patient.getPreviousPathologies().get(0).getName(), "Problemi Cardiovascolari");
        assertEquals(patient.getPreviousPathologies().get(0).getDescription(), "");
    }

    @Test
    void getNumPathologies() throws Exception {
        patient.getPreviousPathologies().clear();
        int oldNum = patient.getNumPathologies();
        patient.addPreviousPathology(new Pathology("Problemi Cardiovascolari", ""));
        assertEquals(oldNum + 1, patient.getNumPathologies());
    }

    @Test
    void testExpectedException() throws MaxPreviousPathologiesException {
        patient.addPreviousPathology(new Pathology("Problemi Cardiovascolari", ""));
        patient.addPreviousPathology(new Pathology("Diabete", ""));
        patient.addPreviousPathology(new Pathology("Ipertensione", ""));
        assertThrows(MaxPreviousPathologiesException.class, () -> patient.addPreviousPathology(new Pathology("Cancro", "")));
    }
}