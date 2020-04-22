package com.covid.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordTest {
    MedicalRecord record;
    CovidPatient patient;

    @BeforeEach
    void setUp() {
        patient = new CovidPatient.CovidPatientBuilder("Paolo", "Neri").
                setRegisteredResidence("Firenze").setAge(67).setPositive(true).build();
        record = new MedicalRecord(patient);
    }

    @Test
    void verifyDefaultStrategy() {
        patient.getPreviousPathologies().clear();
        assertTrue(record.getStrategy() instanceof RecoveryRateNoPathologies);
    }

    @Test
    void testUpdateStrategyChanged() throws MaxPreviousPathologiesException {
        patient.addPreviousPathology(new Pathology("Problemi Cardiovascolari", ""));
        assertTrue(record.getStrategy() instanceof RecoveryRatePreviousPathologies);
    }

    @Test
    void getMedicalRecordID() {
        assertEquals(record.getMedicalRecordID(), "PAONER67FIR");
    }
}