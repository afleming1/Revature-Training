package dev.afleming.problems;

import java.util.ArrayList;
import java.util.List;

public class JavaProblems {
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

    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        int str1Length = str1.length();
        int str2Length = str2.length();

        while (str2Length != 0) {
            int temp = str2Length;
            str2Length = str1Length % str2Length;
            str1Length = temp;
        }

        return str1.substring(0, str1Length);
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();

        int greatestCandy = candies[0];

        for (int i = 0; i < candies.length; i++) {
            if (candies[i] > greatestCandy) {
                greatestCandy = candies[i];
            }
        }

        for (int candy : candies) {
            if (candy + extraCandies >= greatestCandy) {
                result.add(true);
            } else {
                result.add(false);
            }
        }

        return result;
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length - 1; i++) {
            if (flowerbed[i] == n && flowerbed[i + 1] == n) {
                return true;
            }

            if (flowerbed[i] == n && flowerbed[i - 1] == n) {
                return true;
            }
        }

        return false;
    }

    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder reversed = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                reversed.insert(0, word + " ");
            }
        }

        return reversed.toString().trim();
    }

    public boolean uniqueOccurrences(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int uniqueCount = 0;

            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    uniqueCount++;
                }
            }

            if (uniqueCount > 1) {
                return true;
            }
        }

        return false;
    }
}
