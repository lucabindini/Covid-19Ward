package com.covid.domain;

public class MaxPreviousPathologiesException extends Exception{
    public MaxPreviousPathologiesException() {
        super("Puoi inserire fino a 3 patologie pregresse");
    }
}
