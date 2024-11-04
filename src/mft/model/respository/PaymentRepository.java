package mft.model.respository;

import mft.controller.ConnectionProvider;
import mft.model.entity.Payment;
import mft.model.entity.PaymentType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void save(Payment payment) throws Exception {
        connection = ConnectionProvider.getConnection();
        preparedStatement =
                connection.prepareStatement("select product_seq.nextval as next_id from dual");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        payment.setId(resultSet.getInt("next_id"));

        preparedStatement =
                connection.prepareStatement(
                        "insert into products" +
                                " (id, title, amount,date,Description,PaymentType)" +
                                " VALUES (?, ?, ?, ?, ?, ?)"
                );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setString(2, payment.getTitle());
        preparedStatement.setInt(3, payment.getAmount());
        preparedStatement.setDate(4, Date.valueOf(payment.getDateTime()));
        preparedStatement.setString(5, payment.getDescription());
        preparedStatement.setString(6, payment.getPaymentType().toString());
        preparedStatement.execute();
    }



    public void edit(Payment payment) throws Exception {
        connection = ConnectionProvider.getConnection();
        preparedStatement =
                connection.prepareStatement(
                        "update PAYMENT" +
                                " set TITLE=?, AMOUNT=?,DATE_TIME=?, DESCRIPTION=?, PAYMENT_TYPE=?" +
                                " where id=?"
                );
        preparedStatement.setString(1, payment.getTitle());
        preparedStatement.setInt(2, payment.getAmount());
        preparedStatement.setDate(3, Date.valueOf(payment.getDateTime()));
        preparedStatement.setString(4, payment.getDescription());
        preparedStatement.setString(5, payment.getPaymentType().toString());
        preparedStatement.setInt(6, payment.getId());
        preparedStatement.execute();}



    public void remove(int id) throws Exception {
        connection = ConnectionProvider.getConnection();
        preparedStatement =
                connection.prepareStatement(
                        "delete from payment where id=?"
                );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Payment> findAll() throws Exception {
        connection = ConnectionProvider.getConnection();
        preparedStatement =
                connection.prepareStatement("select * from payment order by name");

        ResultSet resultset = preparedStatement.executeQuery();

        List<Payment> paymentList = new ArrayList<>();
        while (resultset.next()) {
            Payment payment =
                    payment
                            .builder()
                            .id(resultset.getInt("id"))
                            .title(resultset.getString("title"))
                            .amount(resultset.getInt("amount"))
                            .dateTime(resultset.getDate("date").toLocalDate())
                            .description(resultset.getString("description"))
                            .paymentType(PaymentType.valueOf(resultset.getString("paymentType")))
                            .build();
            paymentList.add(payment);
        }
        return paymentList;
    }



    public  List<Payment> findByName(String name) throws Exception {
        connection = ConnectionProvider.getConnection();
        preparedStatement =
                connection.prepareStatement("select * from payment where name like ?");
        preparedStatement.setString(1, name);

        ResultSet resultset = preparedStatement.executeQuery();

        List<Payment> paymentList = new ArrayList<>();
        while (resultset.next()) {
            Payment payment =
                    payment
                            .builder()
                            .id(resultset.getInt("id"))
                            .title(resultset.getString("title"))
                            .amount(resultset.getInt("amount"))
                            .dateTime(resultset.getDate("date").toLocalDate())
                            .description(resultset.getString("description"))
                            .paymentType(PaymentType.valueOf(resultset.getString("paymentType")))
                            .build();
            paymentList.add(payment);
        }
        return paymentList;
    }


        @Override
        public void close() throws Exception {
            preparedStatement.close();
            connection.close();
    }
}
