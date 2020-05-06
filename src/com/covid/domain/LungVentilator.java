package com.covid.domain;

public class LungVentilator {
    private final LungVentilatorsTypologies typology;
    private CovidPatient patient;
    private boolean isBusy;

    public LungVentilator(LungVentilatorsTypologies typology) {
        this.typology = typology;
        patient = null;
        isBusy = false;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public LungVentilatorsTypologies getTypology() {
        return typology;
    }

    public CovidPatient getPatient() {
        return patient;
    }

    public void setPatient(CovidPatient patient){
        this.patient = patient;
        isBusy = true;
    }

    public void free() {
        patient = null;
        isBusy = false;
    }

}
