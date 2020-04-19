package com.covid.view;

import com.covid.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField specializationTextField;
    private JButton loginButton;

    public LoginFrame(MainController controller) {
        super("Login");
        setContentPane(mainPanel);
        setSize(400, 500);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (!nameTextField.getText().equals("") && !surnameTextField.getText().equals("") && !specializationTextField.getText().equals("")) {
                    controller.setDirector(nameTextField.getText(), surnameTextField.getText(), specializationTextField.getText());
                    LoginFrame.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Input non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
