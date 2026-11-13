package com.revature.control;

public class Switches {
    public static void main(String[] args) {
        String userInput = "w";

        // We are declaring a switch statement - we must provide a value that will be matched against various "cases"
        // Once a case is found that matches, going from first to last, all following cases will trigger unless you break out of the switch statement
        switch (userInput) {
            case "w":
                System.out.println("User moves up");
                break;
            case "a":
                System.out.println("User moves left");
                break;
            case "s":
                System.out.println("User moves down");
                break;
            case "d":
                System.out.println("User moves right");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
}
