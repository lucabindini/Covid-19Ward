package com.covid.domain;

public class Doctor {
    private final String name;
    private final String surname;
    private final String specialization;

    public Doctor(String name, String surname, String specialization) {
        this.name = name;
        this.surname = surname;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSpecialization() {
        return specialization;
    }
}
