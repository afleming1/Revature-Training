package com.revature.control;

public class Conditionals {
    public static void main(String[] args) {
        System.out.println(basicIfCheck(10));
        System.out.println(basicIfCheck(11));

        System.out.println(ifElseBlock(20));
        System.out.println(ifElseBlock(15));

        System.out.println(ifElseIfElseBlock(20));
        System.out.println(ifElseIfElseBlock(15));
        System.out.println(ifElseIfElseBlock(10));

        // My Code
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int number : numbers) {
            System.out.println(basicIfCheck(number));
        }
    }

    public static String basicIfCheck(int number) {
        String isEven = "Even";

        // If the logical check made below results in a "true" boolean, the associated code block will be executed
        if (number % 2 != 0) {
            isEven = "Odd";
        }

        return isEven;
    }

    public static String ifElseBlock(int age) {
        // You can set up if/else blocks when you want one of two flows of execution to trigger with your code
        // If the "if" statement results in a true boolean, its associated block will execute, but if it results in a false boolean, then the else block triggers
        if (age >= 18) {
            return "You are allowed to vote!";
        } else {
            return "You are not allowed to vote!";
        }
    }

    public static String ifElseIfElseBlock(int age) {
        // If you have multiple conditions to check, you can chain "else if" blocks after your initial if statement.
        // The first logical check that passes is the one that is executed - no other checks are made in the chain.
        // You need to prioritize your logical checks and their associated code if you ever make a chain like this

        String message;

        if (age >= 18) {
            message = "Enjoy the movie!";
        } else if (age >= 13 && age <= 17) {
            message = "Do you have a parent with you?";
        } else {
            message = "You are too young to watch movies!";
        }

        return message;
    }
}
