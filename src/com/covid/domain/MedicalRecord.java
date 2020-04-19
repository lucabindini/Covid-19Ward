package com.covid.domain;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ThreadLocalRandom;

public class MedicalRecord implements Observer {
    private final CovidPatient patient;
    private final String medicalRecordID;
    private RecoveryRateStrategy strategy;

    public MedicalRecord(CovidPatient patient) {
        this.patient = patient;
        medicalRecordID = generateCode(patient);
        patient.addObserver(this);
        strategy = new RecoveryRateNoPathologies();
    }

    private String generateCode(CovidPatient patient) {
        String code = "";
        if(patient.getName().length()>2)
            code += (patient.getName().substring(0, 3)).toUpperCase();
        if(patient.getSurname().length()>2)
            code += (patient.getSurname().substring(0, 3)).toUpperCase();
        code += Integer.toString(patient.getAge());
        if(patient.getRegisteredResidence().length()>2)
            code += (patient.getRegisteredResidence().substring(0, 3)).toUpperCase();
        code+= ThreadLocalRandom.current().nextInt(100, 999 + 1);
        return code;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (patient.getNumPathologies() > 0)
            setStrategy(new RecoveryRatePreviousPathologies());
        else
            setStrategy(new RecoveryRateNoPathologies());
    }

    private void setStrategy(RecoveryRateStrategy strategy){
        this.strategy = strategy;
    }

    public double getRecoveryRate(){
        return strategy.recoveryRate(patient);
    }

    public String getMedicalRecordID() {
        return medicalRecordID;
    }
}
