package com.revature.classes;

public class Person {
    // Attributes (Fields) - Meaningful data to class and its behaviors (Methods)
    String name;
    int age;

    /**
     * Constructor - Creates a new instance of the class
     */
    public Person(String name, int age) {
        // "this" - Reference to the object being created by the constructor
        // Provides us with way of distinguishing between the "name" field of an object and the "name" parameter of the constructor
        this.name = name;
        this.age = age;
    }

    /**
     * Behavior (Methods) - How the fields of the class behave
     */
    public void greet() {
        System.out.println("I am " + name + " and I am " + age + " years old.");
    }
}
