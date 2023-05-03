import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Random Int array generation for automating testing data, works independently from Q1,2.
 */
public class RandomArray {

    public static int[][] getRandomMatrix(int arrayCount, int arraySize, int intBound){
        SecureRandom rand = new SecureRandom();
        int[][] matrix = new int[arrayCount][];
        for (int i = 0; i < arrayCount; i++){
            int randSize = rand.nextInt(arraySize)+1;
            int[] array = new int[randSize];
            for (int j = 0; j < randSize; j++){
                array[j] = rand.nextInt(rand.nextInt(intBound)+1);
            }
            Arrays.sort(array);
            matrix[i] = array;

        }
        return matrix;
    }

}

