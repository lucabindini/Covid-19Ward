package com.covid.view;

import com.covid.controller.MainController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private final MainController controller;
    private JPanel mainPanel;
    private JList<Object> patientsList;
    private JList<Object> pathologiesList;
    private JButton addPatientButton;
    private JButton addPathologyButton;
    private JButton dischargeAllNegativesButton;
    private JTextField medicalRecordIDTextField;
    private JTextField patientNameTextField;
    private JTextField patientSurnameTextField;
    private JTextField patientRegisteredResidenceTextField;
    private JTextField patientAgeTextField;
    private JTextField patientNumPathologiesTextField;
    private JTextField recoveryRateTextField;
    private JLabel loggedLabel;
    private JLabel numBedsLabel;
    private JCheckBox positiveCheckBox;

    public MainFrame() {
        super("COVID-19");
        setContentPane(mainPanel);
        setSize(1000, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controller = new MainController(this);
        setListeners();
        JFrame loginFrame = new LoginFrame(controller);
        loginFrame.setVisible(true);
    }

    private void setListeners() {
        patientsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    if (patientsList.getSelectedIndex() != -1) {
                        pathologiesList.setListData(controller.pathologiesList(patientsList.getSelectedIndex()));
                        medicalRecordIDTextField.setText(controller.getMedicalRecordID(patientsList.getSelectedIndex()));
                        patientNameTextField.setText(controller.getPatientName(patientsList.getSelectedIndex()));
                        patientSurnameTextField.setText(controller.getPatientSurname(patientsList.getSelectedIndex()));
                        patientRegisteredResidenceTextField.setText(controller.getPatientRegisteredResidence(patientsList.getSelectedIndex()));
                        patientAgeTextField.setText(Integer.toString(controller.getPatientAge(patientsList.getSelectedIndex())));
                        patientNumPathologiesTextField.setText(controller.getPatientNumPathologies(patientsList.getSelectedIndex()));
                        recoveryRateTextField.setText(Double.toString(controller.getRecoveryRate(patientsList.getSelectedIndex())));
                        positiveCheckBox.setSelected(controller.isPatientPositive(patientsList.getSelectedIndex()));
                    }
                }
            }
        });

        pathologiesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    if (patientsList.getSelectedIndex() != -1) {
                        JOptionPane.showMessageDialog(new JFrame(), controller.getPathologyDescription(patientsList.getSelectedIndex(), pathologiesList.getSelectedIndex()), "Descrizione Patologia", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });

        addPatientButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                JFrame addPatientFrame = new AddPatientFrame(controller);
                addPatientFrame.setVisible(true);
            }
        });

        addPathologyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (patientsList.getSelectedIndex() != -1) {
                    JFrame addPathologyframe = new AddPathologyFrame(controller, patientsList.getSelectedIndex());
                    addPathologyframe.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Seleziona un Paziente", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dischargeAllNegativesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                JOptionPane.showMessageDialog(new JFrame(), "Sono stati dimessi " + controller.dischargeAllNegatives() + " pazienti risultati negativi al tampone", "Dimissione da Reparto", JOptionPane.PLAIN_MESSAGE);
            }
        });

        positiveCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (patientsList.getSelectedIndex() != -1) {
                    controller.setPatientPositive(patientsList.getSelectedIndex(), positiveCheckBox.isSelected());
                }
            }
        });
    }

    public void refresh() {
        patientsList.setListData(controller.patientsList());
        pathologiesList.setListData(new String[0]);
        medicalRecordIDTextField.setText("");
        patientNameTextField.setText("");
        patientSurnameTextField.setText("");
        patientRegisteredResidenceTextField.setText("");
        patientAgeTextField.setText("");
        patientNumPathologiesTextField.setText("");
        recoveryRateTextField.setText("");
        loggedLabel.setText("Dott. " + controller.getDirectorName() + " " + controller.getDirectorSurname() + ", " + controller.getDirectorSpecialization());
        numBedsLabel.setText("Letti Disponibili: " + controller.getAvailableBeds() + " " + "Letti Occupati: " + controller.getOccupiedBeds());
        positiveCheckBox.setSelected(false);
    }
}
