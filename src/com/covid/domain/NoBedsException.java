package com.covid.domain;

public class NoBedsException extends Exception{
    public NoBedsException(){
        super("Nessun letto al momento disponibile");
    }
}
