package com.covid.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathologyTest {
    Pathology pathology;

    @BeforeEach
    void setUp() {
        pathology = new Pathology("Ipertensione", "prova");
    }

    @Test
    void getName() {
        assertEquals(pathology.getName(), "Ipertensione");
    }

    @Test
    void getDescription() {
        assertEquals(pathology.getDescription(), "prova");
    }
}