package mft.model.respository;

import com.mysql.cj.protocol.Resultset;
import mft.model.entity.Product;
import mft.model.entity.enums.Category;
import mft.model.entity.enums.TransactionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductRepository implements AutoCloseable {
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

    public void save(Product product) throws SQLException {
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
        preparedStatement.setString(4, product.getCategory().name());
        preparedStatement.setDate(5, Date.valueOf(product.getExpireDate()));
        preparedStatement.setInt(6, product.getDiscount());
        preparedStatement.setBoolean(7, product.isCatalogue());
        preparedStatement.setBoolean(8, product.isImage());
        preparedStatement.setString(9, product.getTransactionType().name());
        preparedStatement.setInt(10, product.getId());
        preparedStatement.execute();
    }

    public void remove(int id) throws SQLException {
        connect();
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "delete from products where id=?"
                );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Product> findAll() throws SQLException {
        connect();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from products order by name");

        ResultSet resultset = preparedStatement.executeQuery();

        List<Product> productList = new ArrayList<>();
        while (resultset.next()) {
            Product product =
                    Product
                            .builder()
                            .id(resultset.getInt("id"))
                            .name(resultset.getString("name"))
                            .price(resultset.getInt("price"))
                            .quantity(resultset.getInt("quantity"))
                            .category(Category.valueOf(resultset.getString("category")))
                            .expireDate(resultset.getDate("expire_date").toLocalDate())
                            .discount(resultset.getInt("discount"))
                            .catalogue(resultset.getBoolean("catalogue"))
                            .image(resultset.getBoolean("image"))
                            .transactionType(TransactionType.valueOf(resultset.getString("transaction_type")))
                            .build();
            productList.add(product);
        }
        return productList;
    }

    public Product findById(int id) throws SQLException {
        connect();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from products where id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultset = preparedStatement.executeQuery();

        Product product = null;
        if (resultset.next()) {
            product =
                    Product
                            .builder()
                            .id(resultset.getInt("id"))
                            .name(resultset.getString("name"))
                            .price(resultset.getInt("price"))
                            .quantity(resultset.getInt("quantity"))
                            .category(Category.valueOf(resultset.getString("category")))
                            .expireDate(resultset.getDate("expire_date").toLocalDate())
                            .discount(resultset.getInt("discount"))
                            .catalogue(resultset.getBoolean("catalogue"))
                            .image(resultset.getBoolean("image"))
                            .transactionType(TransactionType.valueOf(resultset.getString("transaction_type")))
                            .build();
        }
        return product;
    }

    public Product findByName(String name) throws SQLException {
        connect();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from products where name=?");
        preparedStatement.setString(1, name);

        ResultSet resultset = preparedStatement.executeQuery();

        Product product = null;
        if (resultset.next()) {
            product =
                    Product
                            .builder()
                            .id(resultset.getInt("id"))
                            .name(resultset.getString("name"))
                            .price(resultset.getInt("price"))
                            .quantity(resultset.getInt("quantity"))
                            .category(Category.valueOf(resultset.getString("category")))
                            .expireDate(resultset.getDate("expire_date").toLocalDate())
                            .discount(resultset.getInt("discount"))
                            .catalogue(resultset.getBoolean("catalogue"))
                            .image(resultset.getBoolean("image"))
                            .transactionType(TransactionType.valueOf(resultset.getString("transaction_type")))
                            .build();
        }
        return product;
    }

//    public int getCount(int id) {}


    @Override
    public void close() throws Exception {
        connection.close();
    }
}
