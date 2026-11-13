package com.revature;

import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        basicChallenge();
    }

    private static void basicChallenge() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a Phrase:");
        String phrase = scan.nextLine().toLowerCase();

        StringBuilder cleaned = new StringBuilder();
        for (char c : phrase.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(c);
            }
        }

        String forward = cleaned.toString();
        String reversed = cleaned.reverse().toString();

        if (forward.equals(reversed)) {
            System.out.println("It's a Palindrome!");
        } else {
            System.out.println("It's not a Palindrome!");
        }
    }

    private static void stretchChallenge() {

    }
}
