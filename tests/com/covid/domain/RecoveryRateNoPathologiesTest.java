package com.covid.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecoveryRateNoPathologiesTest {
    RecoveryRateStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new RecoveryRateNoPathologies();
    }

    @Test
    void recoveryRate() {
        CovidPatient patient = new CovidPatient.CovidPatientBuilder("Franco", "Neri").
                setRegisteredResidence("Firenze").setAge((int) (Math.random() * 101)).setPositive(true).build();
        assertTrue(0 <= strategy.recoveryRate(patient) && strategy.recoveryRate(patient) <= 100);
    }
}