package dev.afleming.problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMergeString {
    @Test
    public void testMergeStringAlternatively() {
        String word1 = "abc";
        String word2 = "pqr";

        Assertions.assertEquals("apbqcr", new MergeString().mergeStringAlternatively(word1, word2));
    }
}
