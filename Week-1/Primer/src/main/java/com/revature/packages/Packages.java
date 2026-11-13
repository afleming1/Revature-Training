package com.revature.packages;

import java.util.Arrays;

public class Packages {
    public static void main(String[] args) {
        Arrays.toString(new int[]{1, 2, 3});
    }

    public static void methodIWantToUseElsewhere() {
        System.out.println("This method is defined somewhere else in my code.");
    }
}
