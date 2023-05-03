import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Q2Test {
    // Test with matrix provided in project document
    @Test
    void Test1() {
        System.out.println("Test1");
        int[][] matrix1 = {{3,1},{0,2},{3,1},{2,0},{}};
        int[][] matrix2 = {{1},{0,2},{3,1},{2}};

        int[] expected = {0,2};
        int[] actual = Q2.Q2(matrix1,matrix2);
        System.out.println("expected: " + Arrays.toString(expected));
        System.out.println("actual: " + Arrays.toString(actual) + "\n");
        assertArrayEquals(expected, actual);
    }
    // Test with expected 0,0 output
    @Test
    void Test2() {
        System.out.println("Test2");
        int[][] matrix1 = {};
        int[][] matrix2 = {{1}};

        int[] expected = {0,0};
        int[] actual = Q2.Q2(matrix1,matrix2);
        System.out.println("expected: " + Arrays.toString(expected));
        System.out.println("actual: " + Arrays.toString(actual) + "\n");
        assertArrayEquals(expected, actual);
    }
    // Test with inversed arrays
    @Test
    void Test3() {
        System.out.println("Test3");
        int[][] matrix1 = {{0,1},{7,8}};
        int[][] matrix2 = {{8,7},{1,0}};

        int[] expected = {0,1};
        int[] actual = Q2.Q2(matrix1,matrix2);
        System.out.println("expected: " + Arrays.toString(expected));
        System.out.println("actual: " + Arrays.toString(actual) + "\n");
        assertArrayEquals(expected, actual);
    }
    // Test with same arrays
    @Test
    void Test4() {
        System.out.println("Test4");
        int[][] matrix1 = {{0,1},{2,3},{4,5},{6,7}};
        int[][] matrix2 = {{0,1},{2,3},{4,5},{6,7}};

        int[] expected = {0,1};
        int[] actual = Q2.Q2(matrix1,matrix2);
        System.out.println("expected: " + Arrays.toString(expected));
        System.out.println("actual: " + Arrays.toString(actual) + "\n");
        assertArrayEquals(expected, actual);
    }
    // Test with null matrix
    @Test
    void Test5() {
        System.out.println("Test5");
        int[][] matrix1 = {};
        int[][] matrix2 = {};

        int[] expected = {0,0};
        int[] actual = Q2.Q2(matrix1,matrix2);
        System.out.println("expected: " + Arrays.toString(expected));
        System.out.println("actual: " + Arrays.toString(actual) + "\n");
        assertArrayEquals(expected, actual);
    }
}
