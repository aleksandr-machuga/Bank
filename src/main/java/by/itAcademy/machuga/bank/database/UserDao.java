package by.itAcademy.machuga.bank.database;

import java.sql.*;

public class UserDao {

    private static final String USER_ID_COLUMN = "userId";
    private static final String NAME_COLUMN = "name";
    private static final String ADDRESS_COLUMN = "address";
    private static final String SELECT_FROM_USERS =
            "SELECT * FROM Users WHERE "
                    + NAME_COLUMN + " = '%s';";
    private static final String GET_USER_ID =
            "SELECT " + USER_ID_COLUMN
                    + " FROM Users WHERE "
                    + NAME_COLUMN + " = '%s';";
    private static final String ADD_USER =
            "INSERT INTO Users ("
                    + NAME_COLUMN + ", "
                    + ADDRESS_COLUMN + ") VALUES('%s', '%s')";

    public static boolean createUser(String name, String address) {
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format(ADD_USER, name, address));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkUserExist(String name) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(String.format(SELECT_FROM_USERS, name));
             ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getUserIdByName(String name) {
        int userId = -1;
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(String.format(GET_USER_ID, name));
             ResultSet resultSet = statement.executeQuery()) {
            userId = resultSet.getInt(USER_ID_COLUMN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
}
