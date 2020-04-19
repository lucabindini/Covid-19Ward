package com.covid.view;

import com.covid.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddPathologyFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton inserisciPatologiaButton;

    public AddPathologyFrame(MainController controller, int patientIndex) {
        super("Inserisci Patologia");
        setContentPane(mainPanel);
        setSize(400, 500);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inserisciPatologiaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (!textField1.getText().equals("") && !textField2.getText().equals("")) {
                    controller.addPathology(patientIndex,textField1.getText(), textField2.getText());
                    AddPathologyFrame.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Input non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
