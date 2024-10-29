package mft.model.respository;

import mft.model.entity.Customer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerRepository implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;

    // Transactions:
    public void save(Customer customer) throws SQLException {
        connection = ConnectionProvider.getConnection();
        preparedStatement =
                connection.prepareStatement("SELECT customer_seq.nextval AS next_id FROM dual");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        customer.setId(resultSet.getInt("next_id"));

        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "INSERT INTO customers" +
                                " (id, name, familyName, username, password, phone, active)" +
                                " VALUES (?, ?, ?, ?, ?, ?, ?)"
                );
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getFamilyName());
        preparedStatement.setString(4, customer.getUsername());
        preparedStatement.setString(5, customer.getPassword());
        preparedStatement.setString(6, customer.getPhone());
        preparedStatement.setBoolean(7, customer.isActive());
        preparedStatement.execute();
    }

    public void edit(Customer customer) throws SQLException {
        connection = ConnectionProvider.getConnection();

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
        preparedStatement.execute();
    }

    public void remove(int id) throws SQLException {
        connection = ConnectionProvider.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "DELETE FROM customers WHERE id=?"
                );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Customer> findAll() throws SQLException {
        connection = ConnectionProvider.getConnection();
        preparedStatement =
                connection.prepareStatement("SELECT * FROM customers ORDER BY name");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer =
                    Customer
                            .builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .familyName(resultSet.getString("familyName"))
                            .username(resultSet.getString("username"))
                            .password(resultSet.getString("password"))
                            .phone(resultSet.getString("phone"))
                            .active(resultSet.getBoolean("active"))
                            .build();
            customerList.add(customer);
        }

        return customerList;
    }

    public Customer findById(int id) throws SQLException {
        connection = ConnectionProvider.getConnection();
        preparedStatement =
                connection.prepareStatement("SELECT * FROM customers WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Customer customer = null;
        if (resultSet.next()) {
            customer =
                    Customer
                            .builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .familyName(resultSet.getString("familyName"))
                            .username(resultSet.getString("username"))
                            .password(resultSet.getString("password"))
                            .phone(resultSet.getString("phone"))
                            .active(resultSet.getBoolean("active"))
                            .build();
        }

        return customer;
    }

    public List<Customer> findByName(String name) throws SQLException {
        connection = ConnectionProvider.getConnection();
        preparedStatement =
                connection.prepareStatement("SELECT * FROM customers WHERE name LIKE ?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer =
                    Customer
                            .builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .familyName(resultSet.getString("familyName"))
                            .username(resultSet.getString("username"))
                            .password(resultSet.getString("password"))
                            .phone(resultSet.getString("phone"))
                            .active(resultSet.getBoolean("active"))
                            .build();

            customerList.add(customer);
        }

        return customerList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
