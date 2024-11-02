package mft.model.respository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mft.model.entity.Branch;

public class BranchRepository implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;


    public List<Branch> getByTitle() throws SQLException {
        this.connection = ConnectionProvider.getConnection();
        preparedStatement =
                connection.prepareStatement("select * from branches where title like ?");

        ResultSet resSet = preparedStatement.executeQuery();

        List<Branch> branches = new ArrayList<>();
        while (resSet.next()) {
            Branch branch = Branch
                    .builder()
                    .id(resSet.getInt("id"))
                    .title(resSet.getString("title"))
                    .area(resSet.getString("area"))
                    .address(resSet.getString("address"))
                    .phone(resSet.getString("phone"))
                    .active(resSet.getBoolean("active"))
                    .build();
            branches.add(branch);
        }
        return branches;
    }

    public List<Branch> getAll() throws SQLException {
        connection = ConnectionProvider.getConnection();
        preparedStatement = connection.prepareStatement("select * from branches order by title");

        ResultSet resSet = preparedStatement.executeQuery();

        List<Branch> branches = new ArrayList<>();
        while (resSet.next()) {
            Branch branch = Branch.builder()
                    .id(resSet.getInt("id"))
                    .title(resSet.getString("title"))
                    .area(resSet.getString("area"))
                    .address(resSet.getString("address"))
                    .phone(resSet.getString("phone"))
                    .active(resSet.getBoolean("active"))
                    .build();
            branches.add(branch);
        }
        return branches;
    }

    public Branch getByID(int id) throws SQLException {
        connection = ConnectionProvider.getConnection();
        preparedStatement = connection.prepareStatement("select * from branches where id = ?");
        preparedStatement.setInt(1, id);

        ResultSet resSet = preparedStatement.executeQuery();

        Branch branch = null;
        if (resSet.next()) {
            branch = Branch
                    .builder()
                    .id(resSet.getInt("id"))
                    .title(resSet.getString("title"))
                    .area(resSet.getString("area"))
                    .address(resSet.getString("address"))
                    .phone(resSet.getString("phone"))
                    .active(resSet.getBoolean("active"))
                    .build();
        }
        return branch;
    }

    public void remove(int id) throws SQLException {
        connection = ConnectionProvider.getConnection();
        preparedStatement = connection.prepareStatement("delete from branches where id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public void edit(Branch branch) throws SQLException {
        connection = ConnectionProvider.getConnection();
        preparedStatement = connection.prepareStatement("update branches" + " set title=?, area=?, active=?, address=?, phone=? where id=?");

        preparedStatement.setString(1, branch.getTitle());
        preparedStatement.setString(2, branch.getArea());
        preparedStatement.setBoolean(3, branch.isActive());
        preparedStatement.setString(4, branch.getAddress());
        preparedStatement.setString(5, branch.getPhone());
        preparedStatement.setInt(6, branch.getId());
        preparedStatement.execute();
    }

    public void save(Branch branch) throws SQLException {
        connection = ConnectionProvider.getConnection();
        preparedStatement = connection.prepareStatement("select branches_seq as next_id from dual");
        ResultSet resSet = preparedStatement.executeQuery();
        resSet.next();
        branch.setId(resSet.getInt("next_id"));

        preparedStatement = connection.prepareStatement(
                "insert into branches (id, title, area, address, phone, active) values (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, branch.getId());
        preparedStatement.setString(2, branch.getTitle());
        preparedStatement.setString(3, branch.getArea());
        preparedStatement.setString(4, branch.getAddress());
        preparedStatement.setString(5, branch.getPhone());
        preparedStatement.setBoolean(6, branch.isActive());
        preparedStatement.execute();
    }

    @Override
    public void close() throws Exception {
        this.preparedStatement.close();
        this.connection.close();
    }
}
