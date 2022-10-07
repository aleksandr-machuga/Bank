package by.itAcademy.machuga.bank.database;

import java.sql.*;

public class AccountDao {
    private static final String ACCOUNT_ID_COLUMN = "accountId";
    private static final String USER_ID_COLUMN = "userId";
    private static final String CURRENCY_COLUMN = "currency";
    private static final String BALANCE_COLUMN = "balance";
    private static final String ACCOUNTS_TABLE = "Accounts";
    private static final String CREATE_ACCOUNT =
            "INSERT INTO " + ACCOUNTS_TABLE
                    + " (" + USER_ID_COLUMN + ","
                    + BALANCE_COLUMN + ","
                    + CURRENCY_COLUMN + ")" +
                    " VALUES('%d', '%d','%s')";
    private static final String SELECT_FROM_ACCOUNTS =
            "SELECT * FROM " + ACCOUNTS_TABLE + " WHERE "
                    + USER_ID_COLUMN + " = '%d' AND "
                    + CURRENCY_COLUMN + " = '%s';";
    private static final String GET_ACCOUNT_ID =
            "SELECT " + ACCOUNT_ID_COLUMN + " FROM "
                    + ACCOUNTS_TABLE + " WHERE "
                    + USER_ID_COLUMN + " = '%d' AND "
                    + CURRENCY_COLUMN + " = '%s';";
    private static final String GET_BALANCE =
            "SELECT " + BALANCE_COLUMN + " FROM "
                    + ACCOUNTS_TABLE + " WHERE "
                    + ACCOUNT_ID_COLUMN + " = '%d' ";
    private static final String INCREASE_BALANCE =
            "UPDATE " + ACCOUNTS_TABLE
                    + " SET " + BALANCE_COLUMN + " = "
                    + BALANCE_COLUMN + " + %d" + " WHERE "
                    + ACCOUNT_ID_COLUMN + " = %d;";
    private static final String DECREASE_BALANCE =
            "UPDATE " + ACCOUNTS_TABLE
                    + " SET " + BALANCE_COLUMN + " = "
                    + BALANCE_COLUMN + " - %d" + " WHERE "
                    + ACCOUNT_ID_COLUMN + " = %d;";

    public static boolean createAccount(int userId, String currency) {
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    String.format(CREATE_ACCOUNT, userId, 0, currency));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkAccountExist(int userId, String currency) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(String.format(SELECT_FROM_ACCOUNTS, userId, currency));
             ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getAccountId(int userId, String currency) {
        int accountId = -1;
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(String.format(GET_ACCOUNT_ID, userId, currency));
             ResultSet resultSet = statement.executeQuery()) {
            return resultSet.getInt(ACCOUNT_ID_COLUMN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountId;
    }

    public static boolean replenish(int accountId, int amount) {
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    String.format(INCREASE_BALANCE, amount, accountId));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean withdraw(int accountId, int amount) {
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    String.format(DECREASE_BALANCE, amount, accountId));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getBalance(int accountId) {
        int balance = -1;
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(String.format(GET_BALANCE, accountId));
             ResultSet resultSet = statement.executeQuery()) {
            return resultSet.getInt(BALANCE_COLUMN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }
}
