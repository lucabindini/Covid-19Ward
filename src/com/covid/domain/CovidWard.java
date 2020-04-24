package com.covid.domain;

import java.util.ArrayList;
import java.util.List;

public class CovidWard {
    private static Doctor director;
    private static List<CovidPatient> patients;
    private static CovidWard instance = null;
    private static int occupiedBeds = 0;
    public static final int MAXIMUM_NUM_BEDS = 10;

    private CovidWard(Doctor director) {
        CovidWard.director = director;
        patients = new ArrayList<>();
    }

    public static CovidWard getInstance(Doctor director) {
        if (instance == null) {
            instance = new CovidWard(director);
        }
        return instance;
    }

    public Doctor getDirector() {
        return director;
    }

    public List<CovidPatient> getPatients() {
        return patients;
    }

    public int getOccupiedBeds() {
        return occupiedBeds;
    }

    public void addPatient(CovidPatient patient) throws NoBedsException {
        if (occupiedBeds < MAXIMUM_NUM_BEDS) {
            patients.add(patient);
            occupiedBeds++;
        } else {
            throw new NoBedsException();
        }
    }

    public void removePatient(CovidPatient patient) {
        if (patient != null) {
            patients.remove(patient);
            occupiedBeds--;
        }
    }

    public void removeAllPatients() {
        patients.clear();
        occupiedBeds = 0;
    }

}
