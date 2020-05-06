package com.covid.domain;

import java.util.ArrayList;
import java.util.List;

public class CovidWard {
    private static Doctor director;
    private static List<CovidPatient> patients;
    private static List<MedicalRecord> records;
    private static LungVentilator[] ventilators;
    private static CovidWard instance = null;
    private static int occupiedBeds;
    public static final int MAXIMUM_NUM_BEDS = 10;
    public static final int NUM_VENTILATORS = 6;

    private CovidWard(Doctor director) {
        CovidWard.director = director;
        patients = new ArrayList<>();
        records = new ArrayList<>();
        ventilators = new LungVentilator[NUM_VENTILATORS];
        setUpVentilators();
        occupiedBeds = 0;
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

    public List<MedicalRecord> getRecords() {
        return records;
    }

    public LungVentilator[] getVentilators() {
        return ventilators;
    }

    public int getOccupiedBeds() {
        return occupiedBeds;
    }

    public void addPatient(CovidPatient patient) throws NoBedsException {
        if (occupiedBeds < MAXIMUM_NUM_BEDS) {
            patients.add(patient);
            records.add(new MedicalRecord(patient));
            occupiedBeds++;
        } else {
            throw new NoBedsException();
        }
    }

    public void removePatient(CovidPatient patient) {
        if (patient != null) {
            int index = patients.indexOf(patient);
            patients.remove(patient);
            removeVentilator(patient);
            records.remove(index);
            occupiedBeds--;
        }
    }

    public void removeAllPatients() {
        patients.clear();
        records.clear();
        occupiedBeds = 0;
    }

    private void setUpVentilators() {
        int i;
        for (i = 0; i < (ventilators.length / 2); i++)
            ventilators[i] = new LungVentilator(LungVentilatorsTypologies.POSITIVE_PRESSURE_VENTILATOR);
        for (int j = i; j < ventilators.length; j++)
            ventilators[j] = new LungVentilator(LungVentilatorsTypologies.NEGATIVE_PRESSURE_VENTILATOR);
    }

    public void assignVentilator(CovidPatient patient, LungVentilatorsTypologies typology) throws NoLungVentilatorsException {
        boolean found = false;
        for (int i = 0; i < ventilators.length && !found; i++) {
            if (!ventilators[i].isBusy() && typology == ventilators[i].getTypology()) {
                ventilators[i].setPatient(patient);
                found = true;
            }
        }
        if (!found)
            throw new NoLungVentilatorsException(typology);
    }

    public void removeVentilator(CovidPatient patient) {
        for (LungVentilator ventilator : ventilators) {
            if (ventilator.getPatient() == patient)
                ventilator.free();
        }

    }
}
