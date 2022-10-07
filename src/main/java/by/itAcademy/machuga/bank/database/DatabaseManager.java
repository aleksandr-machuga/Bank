package by.itAcademy.machuga.bank.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String JDBC_DRIVER_PATH = "org.sqlite.JDBC";
    private static final String DATABASE_URL = "jdbc:sqlite:C:\\DB\\bank.db";
    private static final String JDBC_DRIVER_WAS_NOT_FOUND_MESSAGE = "JDBC Driver was not found";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (isDriverExists()) {
            connection = DriverManager.getConnection(DATABASE_URL);
        }
        return connection;
    }

    public static boolean isDriverExists() {
        try {
            Class.forName(JDBC_DRIVER_PATH);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(JDBC_DRIVER_WAS_NOT_FOUND_MESSAGE);
            return false;
        }
    }
}
