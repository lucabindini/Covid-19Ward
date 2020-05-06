package com.covid.domain;

public class NoLungVentilatorsException extends Exception {
    public NoLungVentilatorsException(LungVentilatorsTypologies typology) {
        super("Nessun ventilatore polmonare della tipologia \"" + typology + "\" al momento disponibile");
    }
}
