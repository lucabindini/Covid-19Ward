package com.covid.domain;

public class RecoveryRatePreviousPathologies implements RecoveryRateStrategy {
    @Override
    public double recoveryRate(CovidPatient patient) {
        double rate;
        switch (patient.getNumPathologies()){
            case 1:
                rate = 92.00;
                break;
            case 2:
                rate = 87.00;
                break;
            default:
                rate = 81.00;
        }
        rate += weightedByAge(patient);
        return rate;
    }

    private double weightedByAge(CovidPatient patient) {
        if(patient.getAge() <= 9)
            return 6.90;
        else if(patient.getAge() >= 10 && patient.getAge() <= 19)
            return 4.40;
        else if(patient.getAge() >= 20 && patient.getAge() <= 29)
            return 2.50;
        else if(patient.getAge() >= 30 && patient.getAge() <= 39)
            return 0.90;
        else if(patient.getAge() >= 40 && patient.getAge() <= 49)
            return -2.00;
        else if(patient.getAge() >= 50 && patient.getAge() <= 59)
            return -4.30;
        else if(patient.getAge() >= 60 && patient.getAge() <= 69)
            return -5.50;
        else if(patient.getAge() >= 70 && patient.getAge() <= 79)
            return -6.20;
        else
            return -7.20;
    }
}
