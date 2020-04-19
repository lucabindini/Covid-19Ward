package com.covid.domain;

public interface RecoveryRateStrategy {
    public double recoveryRate(CovidPatient patient);
}