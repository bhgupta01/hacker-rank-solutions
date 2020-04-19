import java.math.BigDecimal;
import java.math.RoundingMode;

public class Solution {
    static long minimumPasses(long m, long w, long p, long t) {
        BigDecimal runningPasses = BigDecimal.ONE;
        if (m >= t || w >= t) {
            return runningPasses.longValue();
        }
        BigDecimal MACHINES = BigDecimal.valueOf(m);
        BigDecimal WORKERS = BigDecimal.valueOf(w);
        BigDecimal TARGET = BigDecimal.valueOf(t);
        BigDecimal candies = MACHINES.multiply(WORKERS);
        BigDecimal passesAtThisRate = TARGET.divide(candies, RoundingMode.CEILING);
        if (candies.compareTo(TARGET) >= 0) {
            return runningPasses.longValue();
        } else if (p >= t) {
            return passesAtThisRate.longValue();
        }

        BigDecimal PRICE = BigDecimal.valueOf(p);
        // start
        BigDecimal minPasses = passesAtThisRate;
        while (candies.compareTo(TARGET) < 0) {
            if (candies.compareTo(PRICE) < 0) {
                BigDecimal passesUntilBuy = PRICE.subtract(candies).divide(MACHINES.multiply(WORKERS), BigDecimal.ROUND_CEILING);
                runningPasses = runningPasses.add(passesUntilBuy);
                candies = candies.add(MACHINES.multiply(WORKERS).multiply(passesUntilBuy));
            }
            if (candies.compareTo(TARGET) >= 0) {
                minPasses = runningPasses;
                break;
            } else if (candies.multiply(BigDecimal.valueOf(2)).compareTo(TARGET) >= 0) {
                minPasses = runningPasses.add(BigDecimal.ONE);
                break;
            }
            passesAtThisRate = TARGET.subtract(candies).divide(MACHINES.multiply(WORKERS), BigDecimal.ROUND_CEILING).add(runningPasses);
            if (minPasses.compareTo(passesAtThisRate) > 0) {
                minPasses = passesAtThisRate;
            }

            BigDecimal[] buyingPower = candies.divideAndRemainder(PRICE);
            candies = buyingPower[1];
            long steps = buyingPower[0].longValue();
            while (steps > 0) {
                int comparison = MACHINES.compareTo(WORKERS);
                if (comparison >= 0) {
                    WORKERS = WORKERS.add(BigDecimal.ONE);
                } else {
                    MACHINES = MACHINES.add(BigDecimal.ONE);
                }
                steps--;
            }
            passesAtThisRate = TARGET.subtract(candies).divide(MACHINES.multiply(WORKERS), BigDecimal.ROUND_CEILING).add(runningPasses);
            if (passesAtThisRate.compareTo(minPasses) > 0) {
                break;
            } else {
                minPasses = passesAtThisRate;
            }
        }

        return minPasses.longValue();
    }

    public static void main(String... args) {
        long[][] inputs = {
//                {3, 13, 13, 1000_000_000_000L, 10}, // timing out
                {5, 2, 10302, 9133131738L, 3584}, // runs shorter than expected, 6583
                {5361, 3918, 8_447_708L, 989_936_375_520L, 5577}, // runs longer than expected, 3577
                {1, 3, 92, 373, 88},
                {3, 8, 71, 487, 18},
                {5, 1, 172, 364, 72},
                {3, 1, 2, 12, 3},
                {1, 2, 1, 60, 4},
                {1, 1, 6, 45, 16},
                {1, 1, 1000_000_000_000L, 1000_000_000_000L, 1000_000_000_000L},
                {4294967297L, 4294967297L, 1000000000000L, 1000000000000L, 1},
        };
        int count = 1;
        for (long[] input : inputs) {
            long result = minimumPasses(input[0], input[1], input[2], input[3]);
            if (result == input[4]) {
                System.out.println("PASSED");
            } else {
                System.out.println("(" + count + ") Expected=" + input[4] + " Actual=" + result);
                count++;
            }
        }
    }
}
