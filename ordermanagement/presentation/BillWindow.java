package org.ordermanagement.presentation;

import org.ordermanagement.dataAccesLayer.BillDAO;
import org.ordermanagement.model.Bill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BillWindow {
    JFrame frame;
    JTable billTable;
    DefaultTableModel model;
    BillDAO billDAO;

    public BillWindow() {
        frame = new JFrame("Bill operations");
        Color LightOrange = Color.decode("#FFFBD9");
        frame.getContentPane().setBackground(LightOrange);
        frame.setLayout(new FlowLayout());
        billDAO = new BillDAO();

        // create JTable
        billTable = new JTable();
        model = new DefaultTableModel();
        Object[] columnsName = new Object[] {"Order ID", "Date", "Total Cost"};
        model.setColumnIdentifiers(columnsName);
        billTable.setModel(model);

        // Add table to frame
        frame.add(new JScrollPane(billTable));

        // Refresh table
        refreshTable();

        // Set frame size and make it visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 640);
        frame.setVisible(true);
    }

    public void refreshTable() {
        model.setRowCount(0);
        List<Bill> bills = billDAO.findAll();
        for (Bill bill : bills) {
            Object[] objs = {bill.orderId(), bill.date(), bill.total()};
            model.addRow(objs);
        }
    }
}