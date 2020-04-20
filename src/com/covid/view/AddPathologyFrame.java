package com.covid.view;

import com.covid.controller.MainController;
import com.covid.domain.MaxPreviousPathologiesException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AddPathologyFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField descriptionTextField;
    private JButton inserisciPatologiaButton;
    private JComboBox<String> pathologiesComboBox;

    public AddPathologyFrame(MainController controller, int patientIndex) {
        super("Inserisci Patologia");
        setContentPane(mainPanel);
        setSize(400, 500);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        try {
            Scanner scanner = new Scanner(new File("./data/pathologies.txt"));
            while (scanner.hasNextLine()) {
                pathologiesComboBox.addItem(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inserisciPatologiaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (!descriptionTextField.getText().equals("")) {
                    try {
                        if(!controller.hasAlreadyPathology(patientIndex,pathologiesComboBox.getItemAt(pathologiesComboBox.getSelectedIndex())))
                            controller.addPathology(patientIndex,pathologiesComboBox.getItemAt(pathologiesComboBox.getSelectedIndex()), descriptionTextField.getText());
                        else
                            JOptionPane.showMessageDialog(new JFrame(), "Patologia già inserita", "Impossibile aggiungere patologia", JOptionPane.ERROR_MESSAGE);
                    } catch (MaxPreviousPathologiesException exception) {
                        JOptionPane.showMessageDialog(new JFrame(), exception.getMessage(), "Impossibile aggiungere patologia", JOptionPane.ERROR_MESSAGE);
                    }
                    AddPathologyFrame.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Input non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
