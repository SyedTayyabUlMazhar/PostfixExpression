package com.company;

import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("Enter infix expression :  ");

        Scanner scanner = new Scanner(System.in);
        String infixExp = scanner.nextLine();

        Postfix postfix = Postfix.postfixFromInfix(infixExp);
        postfix.evaluatePostfix();

        System.out.println("Postfix : " + postfix.getPostfix());
        System.out.println("Result : " + postfix.getResult());

    }
}
