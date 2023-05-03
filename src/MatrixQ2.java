public class MatrixQ2{
    private int[][] matrix;

    public int[][] getMatrix(){
        return matrix;
    }

    public MatrixQ2(int[][] m) {
        matrix = m;
    }

    //wrapper method for recursive mergeSort
    public void mergeSort() {
        //mergeSort int[] : int[][]
        for (int[] a: matrix){
            mergeSort(a);
        }
        // mergeSort int[][], first by size, then by value of int[]
        mergeSort(matrix, 0, matrix.length - 1);
    }

    private void mergeSort(int[][] m, int low, int high) {
        if (low >= high) return;
        int mid = (low + high) / 2;
        mergeSort(m, low, mid);
        mergeSort(m, mid + 1, high);
        merge(m, low, mid, high);
    }

    /**
     * A mergeSort implementation, in each comparison, first compare, size, then compare value.
     * @param m a matrix prepared in Matrix constructor
     * @param low for recursion
     * @param mid for recursion
     * @param high for recursion
     */
    private void merge(int[][] m, int low, int mid, int high) {
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
            int leftALength = left[leftInd].length;
            int rightALength = right[rightInd].length;
            // if left is shorter, push left
            if (leftALength < rightALength)
                m[i++] = left[leftInd++];
            // if left, right is same size, push right if left.indices are bigger
            if (leftALength == rightALength){
                boolean isLEQ = true;       // flag: is less or equal
                for (int ind = 0; ind < left[leftInd].length; ind++){
                    if (left[leftInd][ind] > right[rightInd][ind]){
                        isLEQ = false;
                        break;
                    }
                }
                if (isLEQ)
                    m[i++] = left[leftInd++];
                else
                    m[i++] = right[rightInd++];

            }
            // if right is shorter, push right
            else
                m[i++] = right[rightInd++];
        }

        // handle leftovers
        while (leftInd < leftSize)
            m[i++] = left[leftInd++];

        while (rightInd < rightSize)
            m[i++] = right[rightInd++];
    }


    //wrapper method for recursive mergeSort
    public void mergeSort(int[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    private void mergeSort(int[] a, int low, int high) {
        if (low >= high) return;
        int mid = (low + high) / 2;
        mergeSort(a, low, mid);
        mergeSort(a, mid + 1, high);
        merge(a, low, mid, high);
    }

    /**
     * mergeSort for int[] array.
     * @param a int[] array
     * @param low for recursion
     * @param mid for recursion
     * @param high for recursion
     */
    private void merge(int[] a, int low, int mid, int high) {
        int leftSize = mid - low + 1;
        int rightSize = high - mid;
        int[] left = new int[leftSize];
        int[] right = new int[rightSize];

        for (int i = 0; i < leftSize; i++)
            left[i] = a[low + i];
        for (int i = 0; i < rightSize; i++)
            right[i] = a[mid + 1 + i];

        int leftInd, rightInd, i;
        leftInd = rightInd = 0;
        i = low;


        while (leftInd < leftSize && rightInd < rightSize) {
            if (left[leftInd] <= right[rightInd])
                a[i++] = left[leftInd++];
            else
                a[i++] = right[rightInd++];
        }

        while (leftInd < leftSize)
            a[i++] = left[leftInd++];

        while (rightInd < rightSize)
            a[i++] = right[rightInd++];
    }

    public int[] findEqual(MatrixQ2 other){
        int[][] left = matrix;
        int[][] right = other.getMatrix();
        int readLeft, readRight;
        readLeft = readRight = 0;
        int leftSize, rightSize;
        leftSize = left.length;       //row count
        rightSize = right.length;     //row count


        // iterate through left and right,
        // left.length < right.length: increment readLeft,
        // left.length == right.length: check equals, if !equals increment by comparing
        while (readLeft < leftSize && readRight < rightSize){
            // if left array is shorter, increment left matrix read position
            if (left[readLeft].length < right[readRight].length) {
                readLeft++;
                continue;
            }
            // if right array is shorter, increment right matrix read position
            if (left[readLeft].length > right[readRight].length) {
                readRight++;
                continue;
            }
            // if left array is of same length with right array, start comparison
            for(int i = 0; i < left[readLeft].length; i++){
                // if any index of left, right doesn't match, determine which read position to increment, return to while loop
                if (left[readLeft][i] != right[readRight][i]){
                    boolean incrementLeft = true;
                    if (readLeft + 1 >= leftSize)
                        incrementLeft = false;
                    else if (!(readRight + 1 > rightSize))
                        if (left[readLeft+1].length > right[readRight+1].length)
                            incrementLeft = false;
                    if (incrementLeft) readLeft++;
                    else readRight++;
                    break;
                }
                // if whole array matches, return array as result
                else return left[readLeft];
            }
        }
        // if no match is found when either list ends, return {0,0}
        return new int[] {0,0};

    }
}
