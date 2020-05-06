package com.covid.view;

import com.covid.controller.MainController;
import com.covid.domain.MaxPreviousPathologiesException;
import com.covid.domain.NoLungVentilatorsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class AssignVentilatorFrame extends JFrame {
    private JPanel mainPanel;
    private JComboBox<String> comboBox1;
    private JButton assegnaButton;

    public AssignVentilatorFrame(MainController controller, int patientIndex) {
        super("Ventilazione Polmonare");
        setContentPane(mainPanel);
        setSize(500, 200);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        comboBox1.addItem("VENTILATORE A PRESSIONE POSITIVA");
        comboBox1.addItem("VENTILATORE A PRESSIONE NEGATIVA");

        assegnaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                try {
                    controller.assignPatientVentilator(patientIndex, Objects.requireNonNull(comboBox1.getSelectedItem()).toString());
                } catch (NoLungVentilatorsException exception) {
                    JOptionPane.showMessageDialog(new JFrame(), exception.getMessage(), "Impossibile assegnare ventilatore", JOptionPane.ERROR_MESSAGE);
                }
                AssignVentilatorFrame.this.dispose();
            }
        });

    }
}
