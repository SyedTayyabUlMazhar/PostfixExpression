package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        for (int i=0; i<256; i++)
//        {
//            System.out.println(i + " : " + (char)i);
//        }

        Postfix postfix = Postfix.postfixFromInfix("(5^3-2^2+9^(1/2))/(4*6^3+2)");
        postfix.evaluatePostfix();

        System.out.println(postfix.getResult());

    }
}
