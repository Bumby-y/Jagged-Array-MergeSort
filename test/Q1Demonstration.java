import org.junit.jupiter.api.Test;

/**
 * arrayCount: The number of array in the matrix
 * arraySize: The upper limit of array size (inclusive)
 * intBound: The upper limit of int value in array (inclusive)
 *
 * note: SmallMatrix and MultipleMatrix are more visually pleasing.
 */
class Q1Demonstration {

//    @Test
//    void CustomTest() {
//        int arrayCount, arraySize, intBound;
//        arrayCount = ;
//        arraySize = ;
//        intBound = ;
//        int[][] inMatrix = RandomArray.getRandomMatrix(arrayCount, arraySize, intBound);
//
//        Q1.Q1(inMatrix);
//    }

    @Test
    void TinyMatrix() {
        int arrayCount, arraySize, intBound;
        arrayCount = 8;
        arraySize = 2;
        intBound = 10;
        int[][] inMatrix = RandomArray.getRandomMatrix(arrayCount, arraySize, intBound);

        Q1.Q1(inMatrix);
    }

    @Test
    void SmallMatrix() {
        int arrayCount, arraySize, intBound;
        arrayCount = 10;
        arraySize = 8;
        intBound = 8;
        int[][] inMatrix = RandomArray.getRandomMatrix(arrayCount,arraySize,intBound);

        Q1.Q1(inMatrix);
    }

    @Test
    void MediumMatrix() {
        int arrayCount, arraySize, intBound;
        arrayCount = 20;
        arraySize = 4;
        intBound = 20;
        int[][] inMatrix = RandomArray.getRandomMatrix(arrayCount,arraySize,intBound);

        Q1.Q1(inMatrix);
    }
    @Test
    void MultipleMatrix() {
        int arrayCount, arraySize, intBound;
        arrayCount = 5;
        arraySize = 5;
        intBound = 12;
        int[][][] inMatrix = {RandomArray.getRandomMatrix(arrayCount,arraySize,intBound),RandomArray.getRandomMatrix(arrayCount,arraySize,intBound)};

        Q1.Q1(inMatrix);
    }


}