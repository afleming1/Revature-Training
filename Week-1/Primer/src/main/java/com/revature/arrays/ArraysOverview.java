package com.revature.arrays;

import java.util.Arrays;

public class ArraysOverview {
    public static void main(String[] args) {
        // String[] - Indicates the type for the variable is an Array that stores String data
        // helloWorld - Variable itself
        // {"Hello", "World", "!"} - Each String between the curly braces is an element of the array. This array has room for 3 elements, because we placed 3 elements inside it
        String[] helloWorld = {"Hello", "World", "!"};

        // If you know how many elements need to be stored, but not what those elements will be, you can make an empty array with x slots to hold elements
        // This array holds 3 elements
        String[] helloWorld2 = new String[3];

        // To store data, you must assign the element to the index position
        helloWorld2[1] = "Hello";
        helloWorld2[0] = "World";
        helloWorld2[2] = "!";

        // This prints the memory location
        System.out.println(helloWorld2);

        // We can use the Arrays class to actually print out the content of the array
        System.out.println(Arrays.toString(helloWorld2));

        String[] orderedAndUppercaseWords = Arrays.stream(helloWorld2)
                .sorted()
                // The two map methods do the same thing, but the second option (method reference) is the preferred way
                //.map(word -> word.toUpperCase())
                .map(String::toUpperCase)
                .toArray(String[]::new);

        System.out.println(Arrays.toString(orderedAndUppercaseWords));
    }
}
