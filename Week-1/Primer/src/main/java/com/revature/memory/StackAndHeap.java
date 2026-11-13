package com.revature.memory;

public class StackAndHeap {
    public static void main(String[] args) {
        // Creates a String with the value "Billy" and stores it in the heap
        String billy = "Billy";

        // Creates a primitive value of 20 which stays in the stack
        int age = 20;

        // Creates a stack frame, the actions of this frame must be handled before the main frame can continue execution
        createAStack(billy);
    }

    public static void createAStack(String arg) {
        // This method call also creates a frame stack that must resolve before the createAStack frame itself can be resolved
        System.out.println(arg);
    }
}
