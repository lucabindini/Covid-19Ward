package com.covid.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RecoveryRateNoPathologies implements RecoveryRateStrategy {
    @Override
    public double recoveryRate(CovidPatient patient) {
        try {
            Scanner scanner = new Scanner(new File("./data/recoveryRate.txt"));
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
