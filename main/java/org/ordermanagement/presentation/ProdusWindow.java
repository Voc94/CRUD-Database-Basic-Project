package org.ordermanagement.presentation;

import org.ordermanagement.dataAccesLayer.ProdusDAO;
import org.ordermanagement.model.Produs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ProdusWindow {
    JFrame frame;
    JTextField idField, numeField, pretField, stocField;
    JButton addButton, editButton, deleteButton, viewButton;
    JTable produsTable;
    DefaultTableModel model;
    ProdusDAO produsDAO;

    public ProdusWindow() {
        frame = new JFrame("Produs operations");
        frame.setLayout(new FlowLayout());
        produsDAO = new ProdusDAO();
        Color LightOrange = Color.decode("#FFFBD9");
        frame.getContentPane().setBackground(LightOrange);
        // create text fields
        idField = new JTextField("Id");
        numeField = new JTextField("Nume");
        pretField = new JTextField("Pret");
        stocField = new JTextField("Stoc");

        // create buttons
        addButton = new JButton("Add produs");
        editButton = new JButton("Edit produs");
        deleteButton = new JButton("Delete produs");
        viewButton = new JButton("View produse");

        // Add event listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produs produs = new Produs(0, numeField.getText(), Float.parseFloat(pretField.getText()), Integer.parseInt(stocField.getText()));
                produsDAO.insert(produs);
                refreshTable();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produs produs = new Produs(Integer.parseInt(idField.getText()), numeField.getText(), Float.parseFloat(pretField.getText()), Integer.parseInt(stocField.getText()));
                produsDAO.update(produs);
                refreshTable();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                produsDAO.delete(Integer.parseInt(idField.getText()));
                refreshTable();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        // Add MouseListener to text fields
        FocusListener focusListener = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField) e.getSource();
                if (source.getText().equals("Id") || source.getText().equals("Nume")
                        || source.getText().equals("Pret") || source.getText().equals("Stoc")) {
                    source.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField source = (JTextField) e.getSource();
                if (source.getText().isEmpty()) {
                    if (source == idField) {
                        source.setText("Id");
                    } else if (source == numeField) {
                        source.setText("Nume");
                    } else if (source == pretField) {
                        source.setText("Pret");
                    } else if (source == stocField) {
                        source.setText("Stoc");
                    }
                }
            }
        };

        idField.addFocusListener(focusListener);
        numeField.addFocusListener(focusListener);
        pretField.addFocusListener(focusListener);
        stocField.addFocusListener(focusListener);

        // create JTable
        produsTable = new JTable();
        model = new DefaultTableModel();
        Object[] columnsName = new Object[] {"ID", "Nume", "Pret", "Stoc"};
        model.setColumnIdentifiers(columnsName);
        produsTable.setModel(model);

        // add them to frame
        frame.add(idField);
        frame.add(numeField);
        frame.add(pretField);
        frame.add(stocField);
        frame.add(addButton);
        frame.add(editButton);
        frame.add(deleteButton);
        frame.add(viewButton);
        frame.add(new JScrollPane(produsTable));

        // set frame size and make it visible
        frame.setSize(700, 700);
        frame.setVisible(true);
    }

    public void refreshTable() {
        model.setRowCount(0);
        List<Produs> produse = produsDAO.findAll();
        for (Produs produs : produse) {
            Object[] objs = {produs.getId(), produs.getNume(), produs.getPret(), produs.getStoc()};
            model.addRow(objs);
        }
    }
        public static void main(String[] args) {
            new ProdusWindow();
        }
    }

