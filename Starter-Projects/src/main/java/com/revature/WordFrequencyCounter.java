package com.revature;

import java.util.*;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        basicChallenge();
        stretchChallenge();
    }

    private static void basicChallenge() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Text:");
        String text = scan.nextLine();

        String[] splitText = text.split("\\s+");

        Map<String, Integer> wordCounts = new TreeMap<>();
        for (String word : splitText) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordCounts.entrySet());
        sortedEntries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.println("Word: '" + entry.getKey() + "' | Appears: " + entry.getValue() + " Times");
        }
    }

    private static void stretchChallenge() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Text:");

        String text = scan.nextLine().toLowerCase().replaceAll("[^a-z0-9\\s]", "");

        String[] splitText = text.split("\\s+");

        Map<String, Integer> wordCounts = new TreeMap<>();
        for (String word : splitText) {
            if (!word.isEmpty()) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordCounts.entrySet());
        sortedEntries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.println("Word: " + entry.getKey() + " | Appears: " + entry.getValue() + " Times");
        }
    }
}
