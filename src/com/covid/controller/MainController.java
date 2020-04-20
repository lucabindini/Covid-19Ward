package com.covid.controller;

import com.covid.domain.*;
import com.covid.view.*;

import java.util.ArrayList;

public class MainController {
    private CovidWard ward;
    private final ArrayList<MedicalRecord> records;
    private final MainFrame view;


    public MainController(MainFrame view) {
        this.view = view;
        records = new ArrayList<>();
    }

    public Object[] patientsList() {
        ArrayList<String> patients = new ArrayList<>();
        for (int i = 0; i < ward.getPatients().size(); i++) {
            patients.add(ward.getPatients().get(i).getName() + " " + ward.getPatients().get(i).getSurname());
        }
        return patients.toArray();
    }

    public Object[] pathologiesList(int patientIndex) {
        ArrayList<String> pathologies = new ArrayList<>();
        for (int i = 0; i < ward.getPatients().get(patientIndex).getPreviousPathologies().size(); i++) {
            pathologies.add(ward.getPatients().get(patientIndex).getPreviousPathologies().get(i).getName());
        }
        return pathologies.toArray();
    }

    public String getDirectorName() {
        return ward.getDirector().getName();
    }

    public String getDirectorSurname() {
        return ward.getDirector().getSurname();
    }

    public String getDirectorSpecialization() {
        return ward.getDirector().getSpecialization();
    }

    public String getPatientName(int patientIndex) {
        return ward.getPatients().get(patientIndex).getName();
    }

    public String getPatientSurname(int patientIndex) {
        return ward.getPatients().get(patientIndex).getSurname();
    }

    public String getPatientRegisteredResidence(int patientIndex) {
        return ward.getPatients().get(patientIndex).getRegisteredResidence();
    }

    public int getPatientAge(int patientIndex) {
        return ward.getPatients().get(patientIndex).getAge();
    }

    public String getPatientNumPathologies(int patientIndex) {
        return Integer.toString(ward.getPatients().get(patientIndex).getNumPathologies());
    }

    public boolean isPatientPositive(int patientIndex) {
        return ward.getPatients().get(patientIndex).isPositive();
    }

    public String getPathologyDescription(int patientIndex, int pathologyIndex) {
        return ward.getPatients().get(patientIndex).getPreviousPathologies().get(pathologyIndex).getDescription();
    }

    public String getMedicalRecordID(int recordIndex) {
        return records.get(recordIndex).getMedicalRecordID();
    }

    public double getRecoveryRate(int recordIndex) {
        return records.get(recordIndex).getRecoveryRate();
    }

    public void setDirector(String name, String surname, String specialization) {
        Doctor director = new Doctor(name, surname, specialization);
        ward = CovidWard.getInstance(director);
        view.refresh();
        view.setVisible(true);
    }

    public void setPatientPositive(int patientIndex, boolean value) {
        ward.getPatients().get(patientIndex).setPositive(value);
    }

    public void addPatient(String name, String surname, String registeredResidence, int age) throws NoBedsException {
        CovidPatient cp = new CovidPatient.CovidPatientBuilder(name, surname).
                setRegisteredResidence(registeredResidence).setAge(age).setPositive(true).build();
        ward.addPatient(cp);
        records.add(new MedicalRecord(cp));
        view.refresh();
    }

    public void addPathology(int patientIndex, String name, String description) throws MaxPreviousPathologiesException{
        Pathology p = new Pathology(name, description);
        ward.getPatients().get(patientIndex).addPreviousPathology(p);
        view.refresh();
    }

    public int dischargeAllNegatives() {
        int index = 0;
        int count = 0;
        while (index < ward.getPatients().size()) {
            if (!ward.getPatients().get(index).isPositive()) {
                ward.getPatients().remove(index);
                ward.setOccupiedBeds(ward.getOccupiedBeds() - 1);
                records.remove(index);
                count++;
            } else {
                index++;
            }
        }
        view.refresh();
        return count;
    }

    public int getAvailableBeds() {
        return CovidWard.MAXIMUM_NUM_BEDS - ward.getOccupiedBeds();
    }

    public int getOccupiedBeds() {
        return ward.getOccupiedBeds();
    }

    public boolean hasAlreadyPathology(int patientIndex, String pathology) {
        for(Pathology p : ward.getPatients().get(patientIndex).getPreviousPathologies()){
            if(p.getName().equalsIgnoreCase(pathology))
                return true;
        }
        return false;
    }
}
