package org.ordermanagement.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame mainFrame;
    private JButton clientButton, produsButton, comenziButton;

    public GUI() {
        mainFrame = new JFrame("Order Management");
        mainFrame.setSize(640, 240);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridBagLayout());
        Color LightOrange = Color.decode("#FFD580");
        mainFrame.getContentPane().setBackground(LightOrange);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        clientButton = new JButton("Place Client");
        produsButton = new JButton("Place Produs");
        comenziButton = new JButton("Place Comanda");

        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientWindow();
                mainFrame.setVisible(false);
            }
        });

        produsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProdusWindow();
                mainFrame.setVisible(false);
            }
        });

        comenziButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ComenziWindow();
                mainFrame.setVisible(false);
            }
        });

        constraints.gridx = 0;
        mainFrame.add(clientButton, constraints);

        constraints.gridx = 1;
        mainFrame.add(produsButton, constraints);

        constraints.gridx = 2;
        mainFrame.add(comenziButton, constraints);

        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
