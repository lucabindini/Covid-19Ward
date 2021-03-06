package com.covid.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class CovidPatient extends Observable {
    private final String name;
    private final String surname;
    private final String registeredResidence;
    private final int age;
    private int saturation;
    private boolean isPositive;
    private final List<Pathology> previousPathologies;

    public static class CovidPatientBuilder {
        private final String name;
        private final String surname;
        private String registeredResidence;
        private int age;
        private boolean isPositive;

        public CovidPatientBuilder(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public CovidPatientBuilder setRegisteredResidence(String registeredResidence) {
            this.registeredResidence = registeredResidence;
            return this;
        }

        public CovidPatientBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public CovidPatientBuilder setPositive(boolean positive) {
            isPositive = positive;
            return this;
        }

        public CovidPatient build() {
            return new CovidPatient(this);
        }

    }

    private CovidPatient(CovidPatientBuilder builder) {
        name = builder.name;
        surname = builder.surname;
        registeredResidence = builder.registeredResidence;
        age = builder.age;
        isPositive = builder.isPositive;
        saturation = 0;
        previousPathologies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getRegisteredResidence() {
        return registeredResidence;
    }

    public int getAge() {
        return age;
    }

    public List<Pathology> getPreviousPathologies() {
        return previousPathologies;
    }

    public int getSaturation() {
        return saturation;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public void setSaturation(int saturation) {
        this.saturation = saturation;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }

    public void addPreviousPathology(Pathology p) throws MaxPreviousPathologiesException {
        if (previousPathologies.size() < 3){
            previousPathologies.add(p);
            setChanged();
            notifyObservers();
        }
        else{
            throw new MaxPreviousPathologiesException();
        }
    }

    public int getNumPathologies() {
        return previousPathologies.size();
    }
}
