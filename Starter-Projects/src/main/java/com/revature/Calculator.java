package com.revature;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        basicChallenge();

        System.out.println("\n");

        stretchChallenge();
    }

    private static void basicChallenge() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter First Number:");
        int num1 = scan.nextInt();

        System.out.println("Enter Second Number:");
        int num2 = scan.nextInt();

        System.out.println("Enter Operator:");
        String operator = scan.next();

        switch (operator) {
            case "+":
                System.out.println(add(num1, num2));
                break;
            case "-":
                System.out.println(subtract(num1, num2));
                break;
            case "*":
                System.out.println(multiply(num1, num2));
                break;
            case "/":
                System.out.println(divide(num1, num2));
                break;
            default:
                System.out.println("Invalid Operator");
                break;
        }
    }

    private static void stretchChallenge() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Enter First Number (or type 'quit' to exit):");
            String firstInput = scan.next();

            if (firstInput.equalsIgnoreCase("quit")) {
                break;
            }

            double num1;
            try {
                num1 = Double.parseDouble(firstInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
                continue;
            }

            System.out.println("Enter Second Number:");
            String secondInput = scan.next();

            double num2;
            try {
                num2 = Double.parseDouble(secondInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
                continue;
            }

            System.out.println("Enter Operator:");
            String operator = scan.next();

            switch (operator) {
                case "+" -> System.out.println("Result: " + add(num1, num2));
                case "-" -> System.out.println("Result" + subtract(num1, num2));
                case "*" -> System.out.println("Result" + multiply(num1, num2));
                case "/" -> System.out.println("Result" + divide(num1, num2));

                default -> System.out.println("Invalid Operator");
            }
        }
    }

    private static double add(double num1, double num2) {
        return num1 + num2;
    }

    private static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    private static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    private static double divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Cannot Divide By Zero");

            return 0;
        }

        return num1 / num2;
    }
}
