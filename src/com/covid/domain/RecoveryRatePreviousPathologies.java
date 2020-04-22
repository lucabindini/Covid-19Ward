package com.covid.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RecoveryRatePreviousPathologies implements RecoveryRateStrategy {
    @Override
    public double recoveryRate(CovidPatient patient) {
        double rate = 100;
        for (int i = 0; i < patient.getPreviousPathologies().size(); i++) {
            rate -= weightedByPathology(patient.getPreviousPathologies().get(i));
        }
        rate += weightedByAge(patient);
        return rate;
    }

    private double weightedByPathology(Pathology pathology) {
        try {
            Scanner scanner = new Scanner(new File("./data/weightedByPathology.txt"));
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().equalsIgnoreCase(pathology.getName()))
                    return Double.parseDouble(scanner.nextLine());
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        }
        return 0;
    }

    private double weightedByAge(CovidPatient patient) {
        try {
            Scanner scanner = new Scanner(new File("./data/weightedByAge.txt"));
            while (scanner.hasNextLine()) {
                if (Integer.parseInt(scanner.nextLine()) == patient.getAge())
                    return Double.parseDouble(scanner.nextLine());
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        }
        return 0;
    }
}
