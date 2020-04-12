package java.gupta.matrix;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

/**
 * Created on 31/01/18 10:09 PM by bhgupta.
 */
public class Solution {

    public static void main(String... args) {
        final Scanner in = new Scanner(System.in);
//        final int rows = in.nextInt();
//        final int cols = in.nextInt();
//        final int[][] arr = new int[rows][cols];
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                arr[i][j] = in.nextInt();
//            }
//        }
        final int[][] arr = {
                {2, 4, 10, 15},
                {6, 8, 14, 20},
                {12, 13, 22, 24},
                {16, 18, 26, 30},
                {17, 25, 27, 32}
        };

//        System.out.println("Enter the element to searched for: ");
        final int[] inputs = {2, 4, 10, 15, 6, 8, 14, 20, 12, 13, 22, 24, 16, 18, 26, 30, 17, 25, 27, 32};
//        final int[] inputs = {40};
//        while (in.hasNext()) {
//            final int n = in.nextInt();
        final Solution obj = new Solution();
        for (int n : inputs) {
            final Instant start = Instant.now();
            final int[] result = obj.searchMatrix(arr, n);
            final Instant end = Instant.now();
            if (result.length > 0) {
                System.out.println("row=" + result[0] + " col=" + result[1]
                        + "\nTook " + Duration.between(start, end).toNanos() + " nanoseconds.");
            } else {
                System.out.println("NOT FOUND"
                        + "\nTook " + Duration.between(start, end).toNanos() + " nanoseconds.");
            }
        }
        in.close();
    }

    private int[] searchMatrix(int[][] arr, int n) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][arr[i].length - 1] < n) {
                continue;
            }

            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == n) {
                    return new int[]{i, j};
                } else if (arr[i][j] > n) {
                    break;
                }
            }
        }
        return new int[]{};
    }
}
