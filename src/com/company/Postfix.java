package com.company;

import java.util.Stack;

public class Postfix {
    private StringBuilder postfix;
    private String result;

    public StringBuilder getPostfix() {
        return postfix;
    }

    public String getResult() {
        return result;
    }

    public Postfix()
    {
        this.postfix = new StringBuilder();
    }

    public static Postfix postfixFromInfix(String infix)
    {
        Postfix postfix = new Postfix();
        infix = "(" + infix + ")";

        Stack<Character> characterStack = new Stack<>();

        for (int i=0; i<infix.length(); i++)
        {
            char currentChar = infix.charAt(i);
            boolean isOperand = isOperand(currentChar);

            if(currentChar=='(')
            {
                characterStack.push(currentChar);
            }
            else if(isOperand(currentChar))
            {
                postfix.getPostfix().append(currentChar);
            }
            else if (isOperator(currentChar))
            {
                while (characterStack.peek()!='(' && comparePrecedence(currentChar, characterStack.peek())<=0)
                {
                    postfix.getPostfix().append(characterStack.peek());
                    characterStack.pop();
                }

                characterStack.push(currentChar);
            }
            else if (currentChar==')')
            {
                while (characterStack.peek()!='(')
                {
                    postfix.getPostfix().append(characterStack.peek());
                    characterStack.pop();
                }

                characterStack.pop();
            }
        }

        return postfix;
    }

    private static boolean isOperand (Character character)
    {
        return (character>=48 && character<=57);
    }

    private static boolean isOperator (char character)
    {
        return (character == '+' || character == '-' || character == '*' || character == '/' || character == '^');
    }

    private static int comparePrecedence(char operator1, char operator2)
    {
        if(
                ((operator1=='+' || operator1 =='-') && (operator2=='+' || operator2 =='-')) ||
                        ((operator1=='*' || operator1 =='/') && (operator2=='*' || operator2 =='/'))
        )
        {
            return 0;
        }
        else if(
                operator1=='^' ||
                        ( (operator1=='*'|| operator1=='/') && (operator2=='+'||operator2=='-') )
        )
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

    public void evaluatePostfix()
    {
        Stack<String> stack = new Stack<>();

        for (int i=0; i<this.postfix.length(); i++)
        {
            char currentChar = this.postfix.charAt(i);

            if(isOperator(currentChar))
            {
                stack.push(
                        solveReverse(String.valueOf(currentChar), stack.pop(), stack.pop())
                );
            }
            else
            {
                stack.push(String.valueOf(currentChar));
            }
        }

        this.result = stack.peek();
    }

    public static String solveReverse(String operator, String operand1, String operand2)
    {
        double operand1AsInt = Double.parseDouble(operand1);
        double operand2AsInt = Double.parseDouble(operand2);

        switch (operator)
        {
            case "+" : return String.valueOf(operand2AsInt + operand1AsInt);
            case "-" : return String.valueOf(operand2AsInt - operand1AsInt);
            case "/" : return String.valueOf(operand2AsInt / operand1AsInt);
            case "*" : return String.valueOf(operand2AsInt * operand1AsInt);
            case "^" : return String.valueOf(Math.pow(operand2AsInt, operand1AsInt));
            default: return "";
        }
    }
}
