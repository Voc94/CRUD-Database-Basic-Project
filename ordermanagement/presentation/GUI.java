package org.ordermanagement.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame mainFrame;
    private JComboBox<String> frameComboBox;
    private JButton openButton;

    public GUI() {
        mainFrame = new JFrame("Order Management");
        mainFrame.setSize(240, 120);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;

        frameComboBox = new JComboBox<>();
        frameComboBox.addItem("Client");
        frameComboBox.addItem("Produs");
        frameComboBox.addItem("Comanda");

        constraints.gridx = 0;
        constraints.gridy = 0;
        mainFrame.add(frameComboBox, constraints);

        openButton = new JButton("Open Frame");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFrame = (String) frameComboBox.getSelectedItem();
                openSelectedFrame(selectedFrame);
            }
        });

        constraints.gridy = 1;
        mainFrame.add(openButton, constraints);

        mainFrame.setVisible(true);
    }

    private void openSelectedFrame(String selectedFrame) {
        switch (selectedFrame) {
            case "Client":
                new ClientWindow();
                break;
            case "Produs":
                new ProdusWindow();
                break;
            case "Comanda":
                new ComenziWindow();
                break;
        }
        mainFrame.setVisible(false);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
