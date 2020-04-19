package com.covid.domain;

public class RecoveryRateNoPathologies implements RecoveryRateStrategy {
    @Override
    public double recoveryRate(CovidPatient patient) {
        if(patient.getAge() <= 9)
            return 100.00;
        else if(patient.getAge() >= 10 && patient.getAge() <= 19)
            return 99.8;
        else if(patient.getAge() >= 20 && patient.getAge() <= 29)
            return 99.8;
        else if(patient.getAge() >= 30 && patient.getAge() <= 39)
            return 99.8;
        else if(patient.getAge() >= 40 && patient.getAge() <= 49)
            return 99.6;
        else if(patient.getAge() >= 50 && patient.getAge() <= 59)
            return 98.7;
        else if(patient.getAge() >= 60 && patient.getAge() <= 69)
            return 96.4;
        else if(patient.getAge() >= 70 && patient.getAge() <= 79)
            return 92.0;
        else
            return 85.2;
    }
}
