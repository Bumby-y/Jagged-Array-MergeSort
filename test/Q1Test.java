import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Note:
 * Only works with Sorted Arrays in this implementation,
 * in the document, it's given that the input will be sorted array,
 * re-checking if arrays are sorted, or re-dividing the levels is not implemented as it is inefficient.
 */
class Q1Test {
    @Test
    void Test1() {
        System.out.println("Test1");
        int[][] matrix1 = {{7,8},{5,6},{3,4},{1,2}};

        int[] expected = {1,2,3,4,5,6,7,8};
        int[] actual = Q1.Q1(matrix1);
        System.out.println("expected: " + Arrays.toString(expected));
        System.out.println("actual: " + Arrays.toString(actual) + "\n");
        assertArrayEquals(expected, actual);
    }
    @Test
    void Test2() {
        System.out.println("Test2");
        int[][] matrix1 = {};

        int[] expected = {};
        int[] actual = Q1.Q1(matrix1);
        System.out.println("expected: " + Arrays.toString(expected));
        System.out.println("actual: " + Arrays.toString(actual) + "\n");
        assertArrayEquals(expected, actual);
    }
    // project document as test data
    @Test
    void Test3() {
        System.out.println("Test3");
        int[][] matrix1 = {{1,2,3,4,5,6,7,8},{9},{10},{11},{12},{13},{14},{15},{16}};

        int[] expected = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        int[] actual = Q1.Q1(matrix1);
        System.out.println("expected: " + Arrays.toString(expected));
        System.out.println("actual: " + Arrays.toString(actual) + "\n");
        assertArrayEquals(expected, actual);
    }
}