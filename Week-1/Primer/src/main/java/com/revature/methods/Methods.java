package com.revature.methods;

public class Methods {
    /**
     * {@code public}: Access modifier<br>
     * {@code void}: This is the return type. Void indicates the method does not return any data<br>
     * {@link doSomething}: Name of the method. We invoke the method via its name<br>
     * {@code {}}: The actual code to be executed when the method is called<br>
     * @param arg (String arg): Parameter of the method. Methods can have multiple parameters.
     */
    public void doSomething(String arg) {
        System.out.println(arg);
    }

    /**
     * doMath returns an int value. The method must return an int or it must throw an exception
     * @param numOne
     * @param numTwo
     * @return
     */
    private int doMath(int numOne, int numTwo) {
        return numOne + numTwo;
    }

    /**
     * If you are unsure of how many arguments will be provided to your method, you can use a variable argument {@code (var arg)}
     * to store however many arguments are provided to your method at runtime.
     * <p>
     * They are saved in an array, and then you can access them through the array
     * <p>
     * Make sure variable arguments are the last parameter in your method signature
     * @param numbers Variable argument
     */
    private static int addAllTheNumbers(int... numbers) {
        int sum = 0;

        for (int number : numbers) {
            sum += number;
        }

        return sum;
    }

    /**
     * The main method is a regular method - you can call it just like any other method
     * @param args Holds any flags / command line arguments provided to the app at runtime
     */
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }

        Methods obj = new Methods();

        // Call a method using the dot operator and reference the method you want to invoke
        obj.doSomething("Hello World!");

        int result = obj.doMath(5, 6);

        int sumOfAllNumbers = addAllTheNumbers(1, 2, 3, 4, 5);
        System.out.println(sumOfAllNumbers);

        int sumOfAllNumbers2 = addAllTheNumbers(6, 7, 8, 9);
        System.out.println(sumOfAllNumbers2);
    }
}
