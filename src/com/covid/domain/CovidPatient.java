package com.covid.domain;

import java.util.ArrayList;
import java.util.List;

public class CovidPatient {
    private final String name;
    private final String surname;
    private final String registeredResidence;
    private final int age;
    private boolean isPositive;
    private final List<Pathology> previousPathologies;

    public CovidPatient(String name, String surname, String registeredResidence, int age, boolean isPositive) {
        this.name = name;
        this.surname = surname;
        this.registeredResidence = registeredResidence;
        this.age = age;
        this.isPositive = isPositive;
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

    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }

    public void addPreviousPathology(Pathology p) {
        previousPathologies.add(p);
    }

    public int getNumPathologies() {
        return previousPathologies.size();
    }

}
