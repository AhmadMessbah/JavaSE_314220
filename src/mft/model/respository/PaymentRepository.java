package mft.model.respository;

import mft.model.entity.Payment;
import java.sql.*;


public class PaymentRepository {
    private Connection connection;
    public void connect() throws SQLException {
        connection =

                DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe",
                        "javase",
                        "java123"
                );}

    public void disconnect() throws SQLException {
            connection.close();
    }

//todo

    public void save(Payment payment) throws SQLException {
        connect();
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into payment" +
                                " (id, title,amount, date_Time, description,payment_Type )" +
                                " VALUES (payment_seq.nextval, ?, ?, ?, ?, ?)"
                        );
        preparedStatement.setString(1, payment.getTitle());
        preparedStatement.setInt(2, payment.getAmount());
        preparedStatement.setDate(3, Date.valueOf(payment.getDateTime()));
        preparedStatement.setString(4, payment.getDescription());
        preparedStatement.setString(5, payment.getPaymentType().toString());
        preparedStatement.execute();
        disconnect();
    }


    public void edit(Payment payment) throws SQLException {
        connect();
        PreparedStatement preparedStatement =
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
        preparedStatement.execute();
        disconnect();
    }
    public void delete(int id) throws SQLException {
        connect();
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "delete from PAYMENT where id=?"
                );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        disconnect();
    }

   }

