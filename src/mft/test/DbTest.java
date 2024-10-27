package mft.test;

import mft.model.entity.Product;
import mft.model.entity.enums.Category;
import mft.model.entity.enums.TransactionType;

import java.sql.*;
import java.time.LocalDate;

public class DbTest {
    public static void main(String[] args) throws SQLException {
        Product product1 =
                Product
                        .builder()
                        .name("Asus 1230")
                        .price(1800)
                        .quantity(3)
                        .discount(0)
                        .category(Category.Electrical)
                        .transactionType(TransactionType.outcome)
                        .expireDate(LocalDate.now())
                        .image(false)
                        .catalogue(true)
                        .build();

        // Connect
        Connection connection =
                DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe",
                        "javase",
                        "java123"
                );

        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "insert into products" +
                                " (id, name, price, quantity, category, expire_date, discount, catalogue, image, transaction_type)" +
                                " VALUES (product_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );
        preparedStatement.setString(1, product1.getName());
        preparedStatement.setInt(2, product1.getPrice());
        preparedStatement.setInt(3, product1.getQuantity());
        preparedStatement.setString(4, product1.getCategory().toString());
        preparedStatement.setDate(5, Date.valueOf(product1.getExpireDate()));
        preparedStatement.setInt(6, product1.getDiscount());
        preparedStatement.setBoolean(7, product1.isCatalogue());
        preparedStatement.setBoolean(8, product1.isImage());
        preparedStatement.setString(9, product1.getTransactionType().toString());
        preparedStatement.execute();

        // Disconnect
        connection.close();
    }
}
