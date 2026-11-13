package com.revature;

import java.util.Arrays;

public class FizzBuzz {
    public static void main(String[] args) {
        int[] numbers = new int[100];
        Arrays.setAll(numbers, i -> i + 1);

        basicChallenge(numbers);

        System.out.println("\n");

        System.out.println(classify(15));
        System.out.println(classify(3));
        System.out.println(classify(5));
    }

    private static void basicChallenge(int... numbers) {
        for (int number : numbers) {
            if (number % 15 == 0) {
                System.out.println("FizzBuzz");
            } else if (number % 3 == 0) {
                System.out.println("Fizz");
            } else if (number % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(number);
            }
        }
    }

    private static String classify(int number) {
        if (number % 15 == 0) {
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(number);
        }
    }
}
