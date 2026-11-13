package com.revature.classes;

public class Classes {
    public static void main(String[] args) {
        Person billy = new Person("Billy", 34);

        billy.greet();

        // Because dogCount is class scoped, we reference it via the class, not an object of the class
        System.out.println("Dog Count: " + Dog.dogCount);

        Dog goodBoy = new Dog("Good Boy", false);
        System.out.println("Dog Count: " + Dog.dogCount); // This works because goodBoy is a Dog object, but it is not idiomatic Java

        Dog goodGirl = new Dog("Good Girl", true);

        goodBoy.barkName();
        goodGirl.barkName();

        System.out.println("Updated Dog Count: " + Dog.dogCount);

        goodBoy.eat();
        goodGirl.eat();
    }
}
