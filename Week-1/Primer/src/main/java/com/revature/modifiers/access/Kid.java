package com.revature.modifiers.access;

public class Kid extends Person {
    public Kid(String name, int age) {
        super(name, age);
    }

    public void shareSecretPassword() {
        System.out.println("Here is my secret community password:");

        communityPassword();
    }

    public void shareSocialSecurityNumber() {
        // The code below does not work because socialSecurityNumber is private, so it cannot be accessed here
        //System.out.println("My social security number is " + socialSecurityNumber);
    }
}
