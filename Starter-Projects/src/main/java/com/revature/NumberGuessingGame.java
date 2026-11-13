package com.revature;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        basicChallenge();

        System.out.println("\n");

        stretchChallenge();
    }

    private static void basicChallenge() {
        int randomNumber = new Random().nextInt(100) + 1;
        int guesses = 0;

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a Random Number Between 1 and 100:");
            int guess = scan.nextInt();

            if (guess == randomNumber) {
                guesses++;
                System.out.println("You Guessed It! Took You " + guesses + " Guesses");
                break;
            } else if (guess < randomNumber) {
                guesses++;
                System.out.println("Too Low!");
            } else {
                guesses++;
                System.out.println("Too High!");
            }
        }
    }

    private static void stretchChallenge() {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        int bestScore = Integer.MAX_VALUE;

        while (true) {
            int randomNumber = random.nextInt(100) + 1;
            int guesses = 0;

            while (true) {
                System.out.println("Enter a Random Number Between 1 and 100:");
                int guess = scan.nextInt();
                guesses++;

                if (guess == randomNumber) {
                    System.out.println("You Guessed It! Took You " + guesses + " Guesses");

                    if (guesses < bestScore) {
                        bestScore = guesses;
                        System.out.println("New Best Score: " + bestScore);
                    }
                    break;
                } else if (guess < randomNumber) {
                    System.out.println("Too Low!");
                } else {
                    System.out.println("Too High!");
                }
            }

            System.out.println("Would you like to play again? (y/n)");
            String playAgain = scan.next();

            if (playAgain.equalsIgnoreCase("n")) {
                System.out.println("Thanks for Playing! Final Best Score: " + bestScore);
                break;
            }
        }
    }
}
