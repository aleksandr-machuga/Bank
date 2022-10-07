package by.itAcademy.machuga.bank.runner;

import by.itAcademy.machuga.bank.service.AccountService;
import by.itAcademy.machuga.bank.service.UserService;
import by.itAcademy.machuga.bank.util.UserInputUtil;

import static by.itAcademy.machuga.bank.interfaces.Constants.*;

public class ApplicationRunner {

    public static void main(String[] args) {
        String action;
        System.out.println(WELCOME_TO_OUR_APPLICATION_MESSAGE);
        do {
            printMenu();
            action = UserInputUtil.readUserInput();
            switch (action) {
                case REGISTER_CHOICE:
                    UserService.addNewUser();
                    break;
                case ACCOUNT_CHOICE:
                    AccountService.addNewAccount();
                    break;
                case REPLENISHMENT_CHOICE:
                    AccountService.replenishAccount();
                    break;
                case WITHDRAW_CHOICE:
                    AccountService.withdrawAccount();
                    break;
                case EXIT_CHOICE:
                    System.out.println(THANKS_FOR_USING_THE_PROGRAM_MESSAGE);
                    break;
                default:
                    System.out.println(WRONG_ACTION_PLEASE_TRY_AGAIN_MESSAGE);
            }
        } while (!EXIT_CHOICE.equals(action));
    }

    private static void printMenu() {
        System.out.println(PLEASE_CHOOSE_THE_ACTION_MESSAGE);
        System.out.println(REGISTER_NEW_USER_MESSAGE);
        System.out.println(CREATE_NEW_ACCOUNT_MESSAGE);
        System.out.println(REPLENISH_THE_ACCOUNT_MESSAGE);
        System.out.println(WITHDRAW_FROM_THE_ACCOUNT_MESSAGE);
        System.out.println(EXIT_MESSAGE);
    }
}
