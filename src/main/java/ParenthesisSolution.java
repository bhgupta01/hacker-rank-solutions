package java.gupta.parenthesis;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created on 03/01/18 8:57 PM by bhgupta.
 */
public class Solution {

    private long counter;

    public static void main(String... args) {
        final Solution obj = new Solution();
        System.out.print("Enter count of java.gupta.parenthesis pair: ");
        final Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            final int n = in.nextInt();
            final Instant start = Instant.now();
            obj.counter = 0;
            final List<String> response = new ArrayList<>();
            obj.generatePattern(n, response);
            final Instant end = Instant.now();
            System.out.println("Patterns: " + obj.counter);
            System.out.println("Took " + Duration.between(start, end).toMillis() + " milliseconds.");

            obj.printList(response);
        }
    }

    void printList(List<String> response) {
        response.forEach(System.out::println);
    }

    void resetCounter() {
        this.counter = 0;
    }

    long getCounter() {
        return counter;
    }

    void generatePattern(int n, List<String> response) {
        if (n > 0) {
            if (n == 1) {
                counter++;
            } else {
                generatePattern(n, n, new char[2 * n], 0, response);
            }
        }
    }

    private void generatePattern(int l, int r, char[] string, int index, List<String> response) {
        if (l <= 0) {
            if (r > 0) {
                while (index < string.length) {
                    string[index++] = ')';
                    r--;
                }
            }
            if (r == 0) {
                final StringBuilder stringBuilder = new StringBuilder();
                for (char c : string) {
                    stringBuilder.append(c);
                }
                response.add(stringBuilder.toString());
                counter++;
                return;
            } else {
                System.err.println("INVALID STRING!!!");
                return;
            }
        }

        if (r >= l) {
            string[index] = '(';
            generatePattern(l - 1, r, string, index + 1, response);
        }

        if (r > l) {
            string[index] = ')';
            generatePattern(l, r - 1, string, index + 1, response);
        }
    }
}
