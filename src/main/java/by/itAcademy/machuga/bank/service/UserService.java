package by.itAcademy.machuga.bank.service;

import by.itAcademy.machuga.bank.database.UserDao;

import static by.itAcademy.machuga.bank.interfaces.Constants.*;
import static by.itAcademy.machuga.bank.util.UserInputUtil.readUserInput;

public class UserService {

    public static void addNewUser() {
        System.out.println(ENTER_NAME_MESSAGE);
        String name = readUserInput();
        if (UserDao.checkUserExist(name)) {
            System.out.printf(USER_WITH_NAME_ALREADY_EXISTS_MESSAGE, name);
            return;
        }
        System.out.println(ENTER_ADDRESS_MESSAGE);
        String addressResponse = readUserInput();
        String address = addressResponse.equalsIgnoreCase(NO_MESSAGE) ? null : addressResponse;
        boolean isUserCreated = UserDao.createUser(name, address);
        if (isUserCreated) {
            System.out.printf(USER_CREATED_MESSAGE, name);
        } else {
            System.out.println(OOPS_SOMETHING_WENT_WRONG_MESSAGE);
            System.out.println(USER_HASN_T_BEEN_CREATED_MESSAGE);
        }
    }
}
