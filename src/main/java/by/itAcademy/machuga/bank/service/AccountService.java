package by.itAcademy.machuga.bank.service;

import by.itAcademy.machuga.bank.database.AccountDao;
import by.itAcademy.machuga.bank.database.UserDao;

import static by.itAcademy.machuga.bank.interfaces.Constants.*;
import static by.itAcademy.machuga.bank.util.UserInputUtil.readUserInput;

public class AccountService {

    public static void addNewAccount() {
        System.out.println(ENTER_YOUR_NAME_MESSAGE);
        String name = readUserInput();
        if (!UserDao.checkUserExist(name)) {
            System.out.println(USER_DOESNT_EXIST_MESSAGE);
            return;
        }
        int userId = UserDao.getUserIdByName(name);
        System.out.println(ENTER_CURRENCY_MESSAGE);
        String currency = readUserInput();
        if (AccountDao.checkAccountExist(userId, currency)) {
            System.out.printf(ACCOUNT_ALREADY_EXISTS_MESSAGE, name, currency);
            return;
        }
        boolean isAccountCreated = AccountDao.createAccount(userId, currency);
        if (isAccountCreated) {
            System.out.printf(ACCOUNT_CREATED_MESSAGE, name, currency);
        } else {
            System.out.println(OOPS_SOMETHING_WENT_WRONG_MESSAGE);
            System.out.println(USER_HASN_T_BEEN_CREATED_MESSAGE);
        }
    }

    public static void replenishAccount() {
        System.out.println(ENTER_YOUR_NAME_MESSAGE);
        String name = readUserInput();
        if (!UserDao.checkUserExist(name)) {
            System.out.println(USER_DOESNT_EXIST_MESSAGE);
            return;
        }
        int userId = UserDao.getUserIdByName(name);
        System.out.println(ENTER_CURRENCY_MESSAGE);
        String currency = readUserInput();
        if (!AccountDao.checkAccountExist(userId, currency)) {
            System.out.printf(ACCOUNT_NOT_EXISTS_MESSAGE, name, currency);
            return;
        }
        int accountId = AccountDao.getAccountId(userId, currency);
        System.out.println(ENTER_AMOUNT_MESSAGE);
        String amountText = readUserInput();
        int amount = Integer.parseInt(amountText);
        if (amount > MAX_TRANSACTION_AMOUNT) {
            System.out.println(AMOUNT_EXCEEDS_MESSAGE);
            return;
        }
        int balance = AccountDao.getBalance(accountId);
        if ((balance + amount) > MAX_BALANCE) {
            System.out.println(BALANCE_EXCEEDS_MESSAGE);
            return;
        }
        boolean isReplenished = AccountDao.replenish(accountId, amount);
        if (isReplenished) {
            System.out.println(ACCOUNT_REPLENISHED_MESSAGE);
            TransactionService.recordTransaction(accountId, amount);
            int realBalance = AccountDao.getBalance(accountId);
            System.out.printf((CURRENT_BALANCE_MESSAGE), realBalance);
        } else {
            System.out.println(OOPS_SOMETHING_WENT_WRONG_MESSAGE);
            System.out.println(ACCOUNT_HASN_T_BEEN_REPLENISHED_MESSAGE);
        }
    }

    public static void withdrawAccount() {
        System.out.println(ENTER_YOUR_NAME_MESSAGE);
        String name = readUserInput();
        if (!UserDao.checkUserExist(name)) {
            System.out.println(USER_DOESNT_EXIST_MESSAGE);
            return;
        }
        int userId = UserDao.getUserIdByName(name);
        System.out.println(ENTER_CURRENCY_MESSAGE);
        String currency = readUserInput();
        if (!AccountDao.checkAccountExist(userId, currency)) {
            System.out.printf(ACCOUNT_NOT_EXISTS_MESSAGE, name, currency);
            return;
        }
        int accountId = AccountDao.getAccountId(userId, currency);
        System.out.println(ENTER_AMOUNT_MESSAGE);
        String amountText = readUserInput();
        int amount = Integer.parseInt(amountText);
        if (amount > MAX_TRANSACTION_AMOUNT) {
            System.out.println(AMOUNT_EXCEEDS_MESSAGE);
            return;
        }
        int balance = AccountDao.getBalance(accountId);
        if (amount > balance) {
            System.out.println(INSUFFICIENT_BALANCE_MESSAGE);
            return;
        }
        boolean isWithdrawn = AccountDao.withdraw(accountId, amount);
        if (isWithdrawn) {
            System.out.println(AMOUNT_WITHDRAWN_MESSAGE);
            TransactionService.recordTransaction(accountId, -amount);
            int realBalance = AccountDao.getBalance(accountId);
            System.out.printf((CURRENT_BALANCE_MESSAGE), realBalance);
        } else {
            System.out.println(OOPS_SOMETHING_WENT_WRONG_MESSAGE);
            System.out.println(AMOUNT_HASN_T_BEEN_WITHDRAWN_MESSAGE);
        }
    }
}
