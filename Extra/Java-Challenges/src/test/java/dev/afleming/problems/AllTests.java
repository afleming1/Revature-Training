package dev.afleming.problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AllTests {
    @Test
    public void testMergeStringAlternatively() {
        String word1 = "abc";
        String word2 = "pqr";

        Assertions.assertEquals("apbqcr", new JavaProblems().mergeStringAlternatively(word1, word2));
    }

    @Test
    public void testGcdOfStrings() {
        String str1 = "ABCABC";
        String str2 = "ABC";

        Assertions.assertEquals("ABC", new GreatestCommonDivisorOfStrings().gcdOfStrings(str1, str2));
    }
}
