package com.revature;

// Importing my own code here. To get access to it here, where the current class is in a separate package, I must import the code
import com.revature.packages.Packages;

public class App {
    public static void main(String[] args) {
        Packages.methodIWantToUseElsewhere();
    }
}
