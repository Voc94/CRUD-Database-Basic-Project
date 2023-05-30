package org.ordermanagement.presentation;

import org.ordermanagement.dataAccesLayer.BillDAO;
import org.ordermanagement.dataAccesLayer.ClientDAO;
import org.ordermanagement.dataAccesLayer.ComenziDAO;
import org.ordermanagement.dataAccesLayer.ProdusDAO;
import org.ordermanagement.presentation.BillWindow;
import org.ordermanagement.model.Bill;
import org.ordermanagement.model.Client;
import org.ordermanagement.model.Comenzi;
import org.ordermanagement.model.Produs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class ComenziWindow extends JFrame {
    private JComboBox<Client> clientComboBox;
    private JComboBox<Produs> productComboBox;
    private JTextField quantityField;
    private JButton orderButton;
    private JButton viewBillsButton;
    private JTextArea messageTextArea;

    public ComenziWindow() {
        setTitle("Order Management");
        setSize(640, 480);
        Color lightOrange = Color.decode("#FFFBD9");
        getContentPane().setBackground(lightOrange);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        clientComboBox = new JComboBox<>();
        productComboBox = new JComboBox<>();
        quantityField = new JTextField();
        orderButton = new JButton("Place Order");
        viewBillsButton = new JButton("View Bills");
        messageTextArea = new JTextArea();
        messageTextArea.setEditable(false);

        add(new JLabel("Select Client:"));
        add(clientComboBox);
        add(new JLabel("Select Product:"));
        add(productComboBox);
        add(new JLabel("Enter Quantity:"));
        add(quantityField);
        add(orderButton);
        add(viewBillsButton);
        add(new JScrollPane(messageTextArea));

        populateComboBoxes();

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeOrder();
            }
        });

        viewBillsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BillWindow();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void populateComboBoxes() {
        ClientDAO clientDAO = new ClientDAO();
        ProdusDAO productDAO = new ProdusDAO();

        List<Client> clients = clientDAO.findAll();
        for (Client client : clients) {
            clientComboBox.addItem(client);
        }

        List<Produs> products = productDAO.findAll();
        for (Produs product : products) {
            productComboBox.addItem(product);
        }
    }

    private void placeOrder() {
        Client selectedClient = (Client)clientComboBox.getSelectedItem();
        Produs selectedProduct = (Produs) productComboBox.getSelectedItem();
        int quantity = Integer.parseInt(quantityField.getText());

        if (quantity > selectedProduct.getStoc()) {
            messageTextArea.setText("Not enough stock!");
            return;
        }

        Comenzi order = new Comenzi();
        order.setIdClient(selectedClient.getId());
        order.setNumeClient(selectedClient.getNume());
        order.setIdProdus(selectedProduct.getId());
        order.setNumeProdus(selectedProduct.getNume());
        order.setCantitate(quantity);
        order.setData(LocalDateTime.now());

        ComenziDAO orderDAO = new ComenziDAO();
        orderDAO.insert(order);

        selectedProduct.setStoc(selectedProduct.getStoc() - quantity);
        ProdusDAO productDAO = new ProdusDAO();
        productDAO.update(selectedProduct);

        // Create Bill
        Bill bill = new Bill(order.getId(), order.getData().toString(), selectedProduct.getPret() * quantity);
        BillDAO billDAO = new BillDAO();
        billDAO.insert(bill);

        messageTextArea.setText("Order placed and bill generated successfully!");
    }

    public static void main(String[] args) {
        new ComenziWindow();
    }
}
