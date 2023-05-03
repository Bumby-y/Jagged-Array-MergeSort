import java.util.Arrays;    //It is only used in visualization for demonstration and debugging and can be completely removed.

/**
 * An interface class that process unsorted 2d/3d int arrays and pass to Priority queue
 */
public class Matrix {
    int[][] matrix;

    // Constructor of int[][]
    public Matrix(int[][] m) {
        matrix = m;
    }

    // Constructor of int[][][] through parsing.
    public Matrix(int[][][] m) {
        int size = 0;
        for (int[][] x : m) {
            size += x.length;
        }
        matrix = new int[size][];
        int i = 0;
        for (int[][] x : m) {
            for (int[] y : x) {
                matrix[i] = y;
                i++;
            }
        }


    }

    //Debug method, visualize the array before and after sort by size
    public void printMatrix() {
        System.out.println("There are " + matrix.length + " arrays in the matrix");
        System.out.print("The sizes in sequence is: ");
        for (int[] x : matrix) {
            System.out.print(x.length + " ");
        }
        System.out.println(".\n");
        int printCount = 0;
        int rowCount = 0;
        for (int[] x : matrix) {
            System.out.print(Arrays.toString(x) + " ");
            if (++printCount >= 5) {
                System.out.println("<Array " + (rowCount * 5 + 1) + " - " + (rowCount * 5 + 5) + "> ,");
                printCount = 0;
                rowCount++;
            }
        }
    }

    //wrapper method for recursive mergeSort
    public void mergeSortBySize() {
        mergeSortBySize(matrix, 0, matrix.length - 1);
    }

    private void mergeSortBySize(int[][] m, int low, int high) {
    if (low >= high) return;
    int mid = (low + high) / 2;
    mergeSortBySize(m, low, mid);
    mergeSortBySize(m, mid + 1, high);
    mergeBySize(m, low, mid, high);
}

    /**
     * A typical mergeSort implementation with a twist, sorting 2d array by the row count.
     * @param m a matrix prepared in Matrix constructor
     * @param low for recursion
     * @param mid for recursion
     * @param high for recursion
     */
    private void mergeBySize(int[][] m, int low, int mid, int high) {
        int leftSize = mid - low + 1;
        int rightSize = high - mid;
        int[][] left = new int[leftSize][];
        int[][] right = new int[rightSize][];

        for (int i = 0; i < leftSize; i++)
            left[i] = m[low + i];
        for (int i = 0; i < rightSize; i++)
            right[i] = m[mid + 1 + i];

        int leftInd, rightInd, i;
        leftInd = rightInd = 0;
        i = low;


        while (leftInd < leftSize && rightInd < rightSize) {
            if (left[leftInd].length <= right[rightInd].length)
                m[i++] = left[leftInd++];
            else
                m[i++] = right[rightInd++];
        }

        while (leftInd < leftSize)
            m[i++] = left[leftInd++];

        while (rightInd < rightSize)
            m[i++] = right[rightInd++];
    }
}
