package mft.model.respository;

import mft.model.entity.Product;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductRepository {
    private Connection connection;

    public void connect() throws SQLException {
        connection =
                DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe",
//                        "jdbc:mysql://localhost:3306/mft" --> mysql
                        "javase",
                        "java123"
                );
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

//    // Transaction
    public void save(Product product) throws SQLException {
//        mysql
//        "insert into products" +
//                " (name, price, quantity, category, expire_date, discount, catalogue, image, transaction_type)" +
//                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"

        connect();
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "insert into products" +
                                " (id, name, price, quantity, category, expire_date, discount, catalogue, image, transaction_type)" +
                                " VALUES (product_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.getCategory().toString());
        preparedStatement.setDate(5, Date.valueOf(product.getExpireDate()));
        preparedStatement.setInt(6, product.getDiscount());
        preparedStatement.setBoolean(7, product.isCatalogue());
        preparedStatement.setBoolean(8, product.isImage());
        preparedStatement.setString(9, product.getTransactionType().toString());
        preparedStatement.execute();
        disconnect();
    }


    public void edit(Product product) throws SQLException {
        connect();
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "update products" +
                                " set name=?, price=?, quantity=?, category=?, expire_date=?, discount=?, catalogue=?, image=?, transaction_type=?" +
                                " where id=?"
                );
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.getCategory().toString());
        preparedStatement.setDate(5, Date.valueOf(product.getExpireDate()));
        preparedStatement.setInt(6, product.getDiscount());
        preparedStatement.setBoolean(7, product.isCatalogue());
        preparedStatement.setBoolean(8, product.isImage());
        preparedStatement.setString(9, product.getTransactionType().toString());
        preparedStatement.setInt(10, product.getId());
        preparedStatement.execute();
        disconnect();
    }

    public void remove(int id) throws SQLException {
        connect();
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "delete from products where id=?"
                );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        disconnect();
    }

//    // Report
//    public Product findById(int id) {}
//
//    public List<Product> findAll() {}
//
//    public List<Product> findByName(String name) {}
//
//    public int getCount(int id) {}

}
