package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/financialsystem";
    private static final String USER = System.getenv("DB_USER"); // Using environment variable
    private static final String PASSWORD = System.getenv("DB_PASSWORD"); // Using environment variable

    static {
        try {
            // Load the JDBC driver explicitly
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC driver not found.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}