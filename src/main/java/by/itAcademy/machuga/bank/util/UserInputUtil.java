package by.itAcademy.machuga.bank.util;

import java.util.Scanner;

public class UserInputUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readUserInput() {
        return scanner.next();
    }
}