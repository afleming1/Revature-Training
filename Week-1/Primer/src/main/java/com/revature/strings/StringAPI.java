package com.revature.strings;

public class StringAPI {
    public static void main(String[] args) {
        // Both variables reference the String "Hello World!"
        // If we were to do this with non-String data, we would actually create two new objects that exist at different memory locations
        String helloWorldOne = "Hello World!";
        String helloWorldTwo = "Hello World!";

        // This equality check returns true, whereas the Object equality check returns false
        // This is because, by default, the "==" operator checks the memory location of the two objects being compared
        // The String variables point to the same String object, so "==" returns true, the two Object variables point to different objects, so "==" returns false
        System.out.println(helloWorldOne == helloWorldTwo);

        Object objectOne = new Object();
        Object objectTwo = new Object();

        System.out.println(objectOne == objectTwo);

        // If you want to make new String object with same text content, you would have to manually invoke the constructor
        String helloWorldThree = new String("Hello World!");

        System.out.println(helloWorldOne == helloWorldThree);

        helloWorldTwo = helloWorldTwo.toUpperCase();

        System.out.println(helloWorldTwo);
        System.out.println(helloWorldOne);

        StringBuilder helloWorldBuilder = new StringBuilder("Hello World!");
        helloWorldBuilder.append(" Isn't it a wonderful morning?");
        System.out.println(helloWorldBuilder);

        int substringStart = helloWorldBuilder.indexOf("wonderful");
        System.out.println(substringStart);

        helloWorldBuilder.replace(substringStart, substringStart + 10, "");
        System.out.println(helloWorldBuilder);

        //testStringStuff();
    }

    private static void testStringStuff() {
        String testString = "This is a test string";

        // Break down testString into individual words, separated by spaces
        String[] words = testString.split(" ");
        for (String word : words) {
            System.out.println(word);
        }

        // Reverse the word array
        for (int i = words.length - 1; i >= 0; i--) {
            System.out.println(words[i]);
        }

        // Reverse the testString String without StringBuilder
        String reversed = "";
        for (int i = testString.length() - 1; i >= 0; i--) {
            reversed += testString.charAt(i);
        }

        System.out.println(reversed);
    }
}
