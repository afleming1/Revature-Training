package dev.afleming;

import dev.afleming.problems.JavaProblems;
import dev.afleming.problems.SQLProblems;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        JavaProblems problems = new JavaProblems();

        //System.out.println(problems.canPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 1));
        //System.out.println(problems.canPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 2));

        //System.out.println(problems.kidsWithCandies(new int[] {2, 3, 5, 1, 3}, 3).toString());
        //System.out.println(problems.kidsWithCandies(new int[] {12, 1, 12}, 10).toString());

        //System.out.println(problems.reverseWords("the sky is blue"));

        //System.out.println(problems.uniqueOccurrences(new int[] {1, 2, 2, 1, 1, 3}));
        //System.out.println(problems.uniqueOccurrences(new int[] {3, 5, -2, -3, -6, -6}));

        doSqlProblems();
    }

    private static void doSqlProblems() throws IOException, SQLException {
        SQLProblems.executeSql();
    }
}
