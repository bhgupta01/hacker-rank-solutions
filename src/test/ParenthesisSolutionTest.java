package java.gupta.parenthesis;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created on 05/01/18 9:26 PM by bhgupta.
 */
public class SolutionTest {

    @DataProvider
    private Object[][] getTestData() {
        return new Object[][]{
                {-10, 0},
                {0, 0},
                {1, 1},
                {5, 42},
                {17, 129644790}
        };
    }

    @Test(dataProvider = "getTestData")
    public void testGeneratePattern(int givenNumberOfPairs, long expectedPatternsCount) {
        final Solution solution = new Solution();
        final List<String> responseStrings = new ArrayList<>();
        solution.resetCounter();
        solution.generatePattern(givenNumberOfPairs, responseStrings);
        assertEquals(solution.getCounter(), expectedPatternsCount);
    }
}
