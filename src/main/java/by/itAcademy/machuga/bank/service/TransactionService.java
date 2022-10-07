package by.itAcademy.machuga.bank.service;

import by.itAcademy.machuga.bank.database.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionService {


    public static final String TRANSACTIONS_TABLE = "Transactions";
    public static final String ACCOUNT_ID_COLUMN = "accountId";
    public static final String AMOUNT_COLUMN = "amount";
    private static final String CREATE_TRANSACTION =
            "INSERT INTO " + TRANSACTIONS_TABLE
                    + " (" + ACCOUNT_ID_COLUMN + ", "
                    + AMOUNT_COLUMN + ") VALUES (%d, %d)";

    public static void recordTransaction(int accountId, int amount) {
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    String.format(CREATE_TRANSACTION, accountId,amount));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}