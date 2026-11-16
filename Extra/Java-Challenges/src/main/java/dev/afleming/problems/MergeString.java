package dev.afleming.problems;

public class MergeString {
    public String mergeStringAlternatively(String word1, String word2) {
        StringBuilder mergedString = new StringBuilder();

        int maxLength = Math.max(word1.length(), word2.length());

        for (int i = 0; i < maxLength; i++) {
            if (i < word1.length()) {
                mergedString.append(word1.charAt(i));
            }

            if (i < word2.length()) {
                mergedString.append(word2.charAt(i));
            }
        }

        return mergedString.toString();
    }
}
