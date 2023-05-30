package org.ordermanagement.dataAccesLayer;

import org.ordermanagement.model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    private static final String INSERT_QUERY = "INSERT INTO Log (orderId, date, total) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Log";

    public void insert(Bill bill) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, bill.orderId());
            preparedStatement.setString(2, bill.date());
            preparedStatement.setDouble(3, bill.total());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
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
        }
        return bills;
    }
}
