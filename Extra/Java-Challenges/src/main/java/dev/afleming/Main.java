package dev.afleming;

import dev.afleming.problems.AllProblems;

public class Main {
    public static void main(String[] args) {
        AllProblems problems = new AllProblems();

        //System.out.println(problems.canPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 1));
        //System.out.println(problems.canPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 2));

        System.out.println(problems.kidsWithCandies(new int[] {2, 3, 5, 1, 3}, 3).toString());
        System.out.println(problems.kidsWithCandies(new int[] {12, 1, 12}, 10).toString());

        System.out.println(problems.reverseWords("the sky is blue"));
    }
}
