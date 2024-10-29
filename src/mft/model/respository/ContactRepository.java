package mft.model.respository;

import mft.model.entity.Contact;

import java.sql.*;

import static javax.rmi.PortableRemoteObject.connect;

public  class ContactRepository {
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

    public void save (Contact contact) throws SQLException {
        connect();
       // PreparedStatement ps = connection.prepareStatement("insert into Contact +" + " values(product_seq.nextval,?,?,?,?)");
    }

    public void edit(Contact contact) {
    }

    public void remove(int id) {

    }
}





