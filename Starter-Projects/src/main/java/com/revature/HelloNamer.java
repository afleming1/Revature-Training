package com.revature;

import java.time.LocalDateTime;
import java.util.Scanner;

public class HelloNamer {
    public static void main(String[] args) {
        basicChallenge();

        System.out.println("\n");

        stretchChallenge(LocalDateTime.now());
    }

    private static void basicChallenge() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Your Name:");

        String name = scan.nextLine();

        System.out.printf("Hello %s!", name);
    }

    private static void stretchChallenge(LocalDateTime time) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Your Name:");

        String name = scan.nextLine();

        if (time.getHour() >= 6 && time.getHour() < 12 ) {
            System.out.printf("Good Morning %s!", name);
        } else if (time.getHour() >= 12 && time.getHour() < 18) {
            System.out.printf("Good Afternoon %s!", name);
        } else {
            System.out.printf("Good Evening %s!", name);
        }
    }
}
