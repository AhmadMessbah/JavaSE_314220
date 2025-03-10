package mft.model.respository;

import mft.model.entity.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void save(Contact contact) throws SQLException {
        connection = ConnectionProvider.getConnection();
        preparedStatement =
                connection.prepareStatement("select Contact_seq.nextval as next_id from dual");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        contact.setId(resultSet.getInt("next_id"));


        preparedStatement =
                connection.prepareStatement("insert into Contact" +
                        " (id, state, city, region, address, postalCode, phone, description)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                );
        preparedStatement.setInt(1, contact.getId());
        preparedStatement.setString(2, contact.getState());
        preparedStatement.setString(3, contact.getCity());
        preparedStatement.setString(4, contact.getRegion());
        preparedStatement.setString(5, contact.getAddress());
        preparedStatement.setString(6, contact.getPostalCode());
        preparedStatement.setString(7, contact.getPhone());
        preparedStatement.setString(8, contact.getDescription());
        preparedStatement.execute();
    }

    public void edit(Contact contact) throws SQLException {
        connection = ConnectionProvider.getConnection();
        preparedStatement =
                connection.prepareStatement(
                        "update Contact" +
                                " set state=?, city=?, region=?, address=?, postalCode=?, phone=?, description=?" +
                                " where id=?"
                );


        preparedStatement.setString(1, contact.getState());
        preparedStatement.setString(2, contact.getCity());
        preparedStatement.setString(3, contact.getRegion());
        preparedStatement.setString(4, contact.getAddress());
        preparedStatement.setString(5, contact.getPostalCode());
        preparedStatement.setString(6, contact.getPhone());
        preparedStatement.setString(7, contact.getDescription());
        preparedStatement.setInt(8, contact.getId());
        preparedStatement.execute();



        connection = ConnectionProvider.getConnection();
        preparedStatement =
                connection.prepareStatement(
                        "delete from Contact where id=?"
                );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }


    public List<Contact> findAll() throws SQLException {
        connection = ConnectionProvider.getConnection();
        preparedStatement =
                connection.prepareStatement("select * from Contact order by name");

        ResultSet resultset = preparedStatement.executeQuery();

        List<Contact> contactsList = new ArrayList<>();
        while (resultset.next()) {
            Contact contact =
                    Contact
                            .builder()
                            .id(resultset.getInt("id"))
                            .state(resultset.getString("state"))
                            .city(resultset.getString("city"))
                            .region(resultset.getString("region"))
                            .address(resultset.getString("address"))
                            .postalCode(resultset.getString("postalCode"))
                            .phone(resultset.getString("phone"))
                            .description(resultset.getString("description"))
                            .build();
            contactsList.add(contact);
        }
        return contactsList;
    }


    public Contact findById(int id) throws SQLException {
        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from contact where id=?");
        preparedStatement.setInt(1, id);

        ResultSet resultset = preparedStatement.executeQuery();

        Contact contact = null;
        if (resultset.next()) {
            contact =
                    Contact
                            .builder()
                            .id(resultset.getInt("id"))
                            .state(resultset.getString("state"))
                            .city(resultset.getString("city"))
                            .region(resultset.getString("region"))
                            .address(resultset.getString("address"))
                            .postalCode(resultset.getString("postalCode"))
                            .phone(resultset.getString("phone"))
                            .description(resultset.getString("description"))
                            .build();
        }
        return contact;
    }


    public List<Contact> findByName(String name) throws SQLException {
        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from contact where name like ?");
        preparedStatement.setString(1, name);

        ResultSet resultset = preparedStatement.executeQuery();

        List<Contact> contactslist = new ArrayList<>();
        while (resultset.next()) {
            Contact contact =
                    Contact
                            .builder()
                            .id(resultset.getInt("id"))
                            .state(resultset.getString("state"))
                            .city(resultset.getString("city"))
                            .region(resultset.getString("region"))
                            .address(resultset.getString("address"))
                            .postalCode(resultset.getString("postalCode"))
                            .phone(resultset.getString("phone"))
                            .description(resultset.getString("description"))
                            .build();
            contactslist.add(contact);
        }
        return contactslist;
    }


    @Override
    public void close() throws Exception {

    }
}
