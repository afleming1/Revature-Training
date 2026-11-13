package com.revature.modifiers.nonAccess;

/**
 * This class cannot be inherited from.
 */
public final class FinalClass {
    final static String finalString = "This reference cannot be changed.";

    public static final void finalMethod() {
        System.out.println("This method cannot be overridden.");
    }
}
