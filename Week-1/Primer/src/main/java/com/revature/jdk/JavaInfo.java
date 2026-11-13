package com.revature.jdk;

public class JavaInfo {
    /**
     * Entrypoint for an application.
     * <p>
     * When you run an app through a class, the entrypoint must be through the main method.
     */
    public static void main(String[] args) {
        // Static Typing
        String greeting = "Hello World!"; // Normal variable declaration

        var newGreeting = "Hello World!"; // New variable declaration

        // Strong Typing
        greeting = "Goodbye World!"; // This is fine, strong typing is maintained
        // newGreeting = 100; // This does not work, strong typing breaks
    }
}
