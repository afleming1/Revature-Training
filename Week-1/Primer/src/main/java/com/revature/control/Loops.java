package com.revature.control;

public class Loops {
    public static void main(String[] args) {
        forLoop(10);

        String[] words = {"Hello", "World", "!"};
        enhancedForLoop(words);

        whileLoop(10);
        doWhileLoop(10);
    }

    /**
     * A standard for loop has 3 primary parts:
     * <ul>
     *     <li>int i = 0</li> Control variable - we use it to determine when the loop ends
     *     <li>i <= iterations</li> Control condition - while the statement produces a true value, the loop continues
     *     <li>i++</li> How we update our control variable to prevent an infinite loop from running
     * </ul>
     */
    private static void forLoop(int iterations) {
        for (int i = 0; i <= iterations; i++) {
            System.out.println(i);

            // If you ever need to end a loop early, the break keywork will stop the loop
            if (i == 7) {
                break;
            }
        }
    }

    /**
     * Whenever you need to iterate over a collection of data, you can typically use an enhanced for loop
     * <ul>
     *     <li>String word</li> Variable that represents the data for the current iteration
     *     <li>words</li> Collection we are iterating through
     * </ul>
     * Once each element of the collection has gone through the loop, it automatically ends
     */
    private static void enhancedForLoop(String[] words) {
        for (String word : words) {
            if (word.equals("Hello")) {
                // Continue tells your loop to enter its next iteration. Useful if you want to skip actions
                continue;
            }

            System.out.println(word);

            if (word.equals("World")) {
                break;
            }
        }
    }

    private static void whileLoop(int iterations) {
        boolean keepIterating = true;
        int count = 1;

        while (keepIterating) {
            System.out.println(count);
            count++;

            if (count > iterations) {
                keepIterating = false;
            }
        }
    }

    private static void doWhileLoop(int iterations) {
        // Unlike other loops where logic checked and code is executed, in a do-while loop, the code is first executed, then the logic is checked
        boolean keepIterating = true;
        int count = 0;

        do {
            System.out.println(count);
            count++;

            if (count > iterations) {
                keepIterating = false;
            }
        } while (keepIterating);
    }
}
