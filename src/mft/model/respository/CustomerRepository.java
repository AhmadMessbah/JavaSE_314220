package mft.model.respository;

import mft.model.entity.Customer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerRepository {
    private Connection connection;

    public void connect() throws SQLException {
        connection =
                DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe",
                        "javase",
                        "java123"
                );
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

    // Transactions:
    public void save(Customer customer) throws SQLException {
        connect();
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "INSERT INTO customers" +
                                " (id, name, familyName, username, password, phone, active)" +
                                " VALUES (customer_seq.nextval, ?, ?, ?, ?, ?, ?)"
                );
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getFamilyName());
        preparedStatement.setString(3, customer.getUsername());
        preparedStatement.setString(4, customer.getPassword());
        preparedStatement.setString(5, customer.getPhone());
        preparedStatement.setBoolean(6, customer.isActive());
        disconnect();
    }

    public void edit(Customer customer) throws SQLException {
        connect();

        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "UPDATE customers" +
                                " SET name=?, familyName=?, username=?, password=?, phone=?, active=?" +
                                " WHERE id=?"
                );

        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getFamilyName());
        preparedStatement.setString(3, customer.getUsername());
        preparedStatement.setString(4, customer.getPassword());
        preparedStatement.setString(5, customer.getPhone());
        preparedStatement.setBoolean(6, customer.isActive());
        preparedStatement.setInt(7, customer.getId());

        disconnect();
    }

    public void remove(Customer customer) throws SQLException {
        connect();

        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "DELETE FROM customers WHERE id=?"
                );

        preparedStatement.setInt(1, customer.getId());

        disconnect();
    }
}
