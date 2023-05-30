package org.ordermanagement.presentation;

import org.ordermanagement.dataAccesLayer.ClientDAO;
import org.ordermanagement.model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ClientWindow {
    JFrame frame;
    JTextField idField, numeField, prenumeField, adresaField, varstaField;
    JButton addButton, editButton, deleteButton, viewButton;
    JTable clientTable;
    DefaultTableModel model;
    ClientDAO clientDAO;

    public ClientWindow() {
        frame = new JFrame("Client operations");
        frame.setLayout(new FlowLayout());
        clientDAO = new ClientDAO();
        Color LightOrange = Color.decode("#FFFBD9");
        frame.getContentPane().setBackground(LightOrange);
        // create text fields
        idField = new JTextField("Id");
        numeField = new JTextField("Nume");
        prenumeField = new JTextField("Prenume");
        adresaField = new JTextField("Adresa");
        varstaField = new JTextField("Varsta");

        // create buttons
        addButton = new JButton("Add client");
        editButton = new JButton("Edit client");
        deleteButton = new JButton("Delete client");
        viewButton = new JButton("View clients");

        // Add event listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client(0, numeField.getText(), prenumeField.getText(), adresaField.getText(), Integer.parseInt(varstaField.getText()),null);
                clientDAO.insert(client);
                refreshTable();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client(Integer.parseInt(idField.getText()), numeField.getText(), prenumeField.getText(), adresaField.getText(), Integer.parseInt(varstaField.getText()),null);
                clientDAO.update(client);
                refreshTable();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientDAO.delete(Integer.parseInt(idField.getText()));
                refreshTable();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        FocusListener focusListener = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField) e.getSource();
                if (source.getText().equals("Id") || source.getText().equals("Nume")
                        || source.getText().equals("Prenume") || source.getText().equals("Adresa")
                        || source.getText().equals("Varsta")) {
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
                    } else if (source == prenumeField) {
                        source.setText("Prenume");
                    } else if (source == adresaField) {
                        source.setText("Adresa");
                    }else if (source == varstaField) {
                        source.setText("Varsta");
                    }
                }
            }
        };

        idField.addFocusListener(focusListener);
        numeField.addFocusListener(focusListener);
        prenumeField.addFocusListener(focusListener);
        adresaField.addFocusListener(focusListener);
        varstaField.addFocusListener(focusListener);
        // create JTable
        clientTable = new JTable();
        model = new DefaultTableModel();
        Object[] columnsName = new Object[] {"ID", "Nume", "Prenume", "Adresa", "Varsta","Email"};
        model.setColumnIdentifiers(columnsName);
        clientTable.setModel(model);

        // add them to frame
        frame.add(idField);
        frame.add(numeField);
        frame.add(prenumeField);
        frame.add(adresaField);
        frame.add(varstaField);
        frame.add(addButton);
        frame.add(editButton);
        frame.add(deleteButton);
        frame.add(viewButton);
        frame.add(new JScrollPane(clientTable));

        // set frame size and make it visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setVisible(true);
    }

    public void refreshTable() {
        model.setRowCount(0);
        List<Client> clients = clientDAO.findAll();
        for (Client client : clients) {
            Object[] objs = {client.getId(), client.getNume(), client.getPrenume(), client.getAdresa(), client.getVarsta()};
            model.addRow(objs);
        }
    }

    public static void main(String[] args) {
        new ClientWindow();
    }
}
