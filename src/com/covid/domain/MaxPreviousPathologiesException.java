package com.covid.domain;

public class MaxPreviousPathologiesException extends Exception{
    public MaxPreviousPathologiesException() {
        super("puoi inserire fino a 3 patologie pregresse");
    }
}
