package System.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mainconnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:2000/rent_store";
        String username = "root";
        String password = "root";

        return DriverManager.getConnection(url, username, password);
    }
}
