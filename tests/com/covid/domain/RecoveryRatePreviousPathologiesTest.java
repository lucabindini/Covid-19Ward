package com.covid.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecoveryRatePreviousPathologiesTest {
    RecoveryRateStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new RecoveryRatePreviousPathologies();
    }

    @Test
    void recoveryRate() throws MaxPreviousPathologiesException {
        CovidPatient patient = new CovidPatient.CovidPatientBuilder("Franco", "Neri").
                setRegisteredResidence("Firenze").setAge((int)(Math.random()*101)).setPositive(true).build();
        patient.addPreviousPathology(new Pathology("Problemi Cardiovascolari",""));
        assertTrue(0 <= strategy.recoveryRate(patient) && strategy.recoveryRate(patient) <= 100);
    }
}