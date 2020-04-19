package com.covid.domain;

import java.util.ArrayList;
import java.util.List;

public class CovidWard {
    private static Doctor director;
    private static List<CovidPatient> patients;
    private static CovidWard instance = null;
    private static int occupiedBeds;
    public static final int MAXIMUM_NUM_BEDS = 10;

    private CovidWard() {}

    public static CovidWard getInstance(Doctor director) {
        if (instance == null) {
            instance = new CovidWard();
            instance.setDirector(director);
            instance.setPatients(new ArrayList<>());
            instance.setOccupiedBeds(0);
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


    public void setDirector(Doctor director) {
        CovidWard.director = director;
    }

    public void setPatients(List<CovidPatient> patients) {
        CovidWard.patients = patients;
    }

    public void setOccupiedBeds(int occupiedBeds) {
        CovidWard.occupiedBeds = occupiedBeds;
    }

    public void addPatient(CovidPatient patient) throws Exception{
        if (occupiedBeds < MAXIMUM_NUM_BEDS) {
            patients.add(patient);
            occupiedBeds++;
        }
        else{
            throw new Exception("Nessun letto disponibile");
        }
    }
}
