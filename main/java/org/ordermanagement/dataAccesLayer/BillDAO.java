package org.ordermanagement.dataAccesLayer;

import org.ordermanagement.model.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    public void insert(Bill bill) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Log VALUES (?, ?, ?)");
            preparedStatement.setInt(1, bill.orderId());
            preparedStatement.setString(2, bill.date());
            preparedStatement.setDouble(3, bill.total());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(connection);
        }
    }

    public List<Bill> findAll() {
        Connection connection = ConnectionFactory.getConnection();
        List<Bill> bills = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Log");
            while (resultSet.next()) {
                Bill bill = new Bill(
                        resultSet.getInt("orderId"),
                        resultSet.getString("date"),
                        resultSet.getDouble("total")
                );
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(connection);
        }
        return bills;
    }

}
