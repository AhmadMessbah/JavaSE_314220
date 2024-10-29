package mft.model.respository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    public static Connection getConnection() throws SQLException {
        return
                DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe",
                        "javase",
                        "java123"
                );
    }
}

