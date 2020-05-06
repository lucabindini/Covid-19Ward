package com.covid.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LungVentilatorTest {
    LungVentilator ventilator;

    @BeforeEach
    void setUp() {
        ventilator = new LungVentilator(LungVentilatorsTypologies.POSITIVE_PRESSURE_VENTILATOR);
    }

    @Test
    void isBusy() {
        ventilator.setPatient(new CovidPatient.CovidPatientBuilder("Mario", "Neri").
                setRegisteredResidence("Firenze").setAge(78).setPositive(true).build());
        assertTrue(ventilator.isBusy());
    }

    @Test
    void getTypology() {
        assertEquals(ventilator.getTypology(), LungVentilatorsTypologies.POSITIVE_PRESSURE_VENTILATOR);
    }

    @Test
    void setPatient() {
        ventilator.setPatient(new CovidPatient.CovidPatientBuilder("Gianni", "Rossi").
                setRegisteredResidence("Firenze").setAge(85).setPositive(true).build());
        assertEquals(ventilator.getPatient().getName(), "Gianni");
        assertEquals(ventilator.getPatient().getSurname(), "Rossi");
        assertEquals(ventilator.getPatient().getRegisteredResidence(), "Firenze");
        assertEquals(ventilator.getPatient().getAge(), 85);
        assertTrue(ventilator.getPatient().isPositive());
    }

    @Test
    void free() {
        ventilator.setPatient(new CovidPatient.CovidPatientBuilder("Mauro", "Bianchi").
                setRegisteredResidence("Firenze").setAge(87).setPositive(true).build());
        ventilator.free();
        assertFalse(ventilator.isBusy());
        assertNull(ventilator.getPatient());
    }
}