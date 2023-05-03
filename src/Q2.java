@SuppressWarnings("all")
/**
 * Previously, in Q1, mergeSort by size is implemented before feeding the data into the
 * main mergeSort algorithm, to achieve better efficiency on sorting jagged arrays.
 *
 * flow:
 * input as 2d/3d array,
 * construct Matrix object,
 * mergeSort by size,
 * put into a linkedlist Stack by size,
 * pop Stack from low size to high size, into mergeSort, reinsert.
 * output the last node.array
 *
 * In Q1, since the main mergeSort is a implicit requirement, only array size is
 * important from arrays before passing them into mergeSort.
 *
 * In Q2, however, we could not merge the two matrix into one using mergeSort, or
 * the relation of row i in Matrix m will be lost, and it will be impossible to
 * get the desired output without adding in extra parameters and comparison.
 *
 * My initial plan so far is to create two *sorted* matrix, construct sorted linkedlists(by 1.size, 2. value), and do linear comparison,
 * instead of creating an unsorted linkedlists.
 *
 * Observation:
 *  In Q2, array are not unique (different row with same value),
 *  int are unique within array (indices).
 *
 * Approach:
 *  The majority of the solution will be using Q1's approach,
 *  1. mergeSort arrays: so that an arbitary size array a is sorted
 *  2. mergeSor matrix: first by size, then by value in each level (iterate comparison through int[i]).
 *  3. linear comparison using two read indices/position to traverse through two int[][] matrix (similar to merging in mergeSort)
 *  4. output first equal int[] array in String format..
 *
 * Time complexity: refer number# to [Approach]
 * 1: O(nlogn): between n and nlogn
 * 2: O(nlogn)
 * 3: O(n): 2nc
 * 4. O(1) passing reference value
 **/

public class Q2 {

    public static int[] Q2(int[][] m, int[][] m2) {
        MatrixQ2 matrix1 = new MatrixQ2(m);
        MatrixQ2 matrix2 = new MatrixQ2(m2);
        matrix1.mergeSort();
        matrix2.mergeSort();
        int[] result = matrix1.findEqual(matrix2);
        return result;

    }
}