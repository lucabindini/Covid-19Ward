package com.covid.view;

import com.covid.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddPatientFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox<Object> comboBox1;
    private JButton inserisciPazienteButton;

    public AddPatientFrame(MainController controller){
        super("Inserisci Paziente");
        setContentPane(mainPanel);
        setSize(400, 500);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        for(int i = 0;i<120;i++)
            comboBox1.addItem(Integer.toString(i));
        inserisciPazienteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (!textField1.getText().equals("") && !textField2.getText().equals("") && !textField3.getText().equals("") && !textField3.getText().equals("")) {
                    try {
                        controller.addPatient(textField1.getText(), textField2.getText(), textField3.getText(), comboBox1.getSelectedIndex());
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(new JFrame(), "Nessun letto disponibile", "Impossibile aggiungere paziente", JOptionPane.ERROR_MESSAGE);
                    }
                    AddPatientFrame.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Input non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
