import java.util.Arrays;    //It is only used in visualization for demonstration and debugging and can be completely removed.


@SuppressWarnings("all")
/**
 * 1.Problem:
 * Merge k sorted arrays into a single sorted Array
 * <p>
 * 2.Approach:
 * Linearly sort through the arrays starting from shortest array
 *
 * 3.Operation:
 * Insert: Take input of all arrays from input arrays.
 * Merge: Merge two arrays into a single array.
 * Search next shortest: Find the shortest sorted array
 * Output: return a single array
 *
 * 4.Explicit requirement:
 * O(1) time complexity for finding two shortest array.
 *
 * 5.Derived requirement:
 * There are two approach to achieve O(1),
 * 1. Array direct access through index.
 * 2. List iteration by access next item in list.
 *
 * [insight] both approach requires a way to keep track of this.next
 *           a index structure is required.
 *
 * 6.Implicit requirement:
 *      1. Dynamic memory allocation (from arbitrary length)
 *          1. hash with separate chaining
 *          2. expandable array
 *          3. linked list
*       2. Sorting by length
 *          1.Merge happens after all array input is taken
 *          2.Some way to keep track of array in same length (concept similar to hash collision)
 *
 * 7.Proposed solution:
 *
 * Main approach: (After considering some other approaches)
 *  Structure: Priority queue(queue of stacks):
 *      An outer queue that keep track of array size, each node points to
 *      an inner stacks that stores the actual arrays.
 *
 *      Implementation:
 *      Outer queue: an index linkedlist that keep track of unique array size.
 *          queue: linear linkedlist transversal, create new node if key is not found: O(n)
 *          dequeue : Peek and pop from stack, dequeue then peek next if stack is empty:  O(1)
 *
 *      Inner stacks: linkedlist of array of the same size.
 *          push : When outer queue pass an array, insert new node at head.
 *          pop : when outer queue peek, pops the head node, head.node becomes head.
 *
 * 8. Evaluation and improvement:
 * Time complexity of current solution:
 *      Insert : O(n)
 *      InsertMatrix: O(n*n) = O(n^2)
 *      Merge: O(nlogn) or O(nloga), a = the count of arrays
 *      Search next shortest : O
 *
 *      Highlight: The biggest downfall of this approach is the iterative process of handling input,
 *      since each i in n takes n take, n array will take O(n^2) time. A solution will be a batch sorting process,
 *      so that the O(n^2) insertion can be reduced into O(nlogn).
 *
 *      Plan to improve insertion: take all array input and construct an unsorted array of arrays: O(n),
 *      mergeSort by length: O(nlogn), then construct the priority queue using linear traversal. After each merge,
 *      the output array will be push to the stack in it's respective index node.
 *
 *
 * 9.Summary of solution:
 *   Insert: Linear traversal of index queue then push
 *      time complexity :O(n) / O(i) [i = size of index queue]
 *      space complexity : O(n):
 *          outerQueue: head, next, queueSize, node(key, stackHead)
 *          innerStack: head, next, stackSize
 *
 *   InsertMatrix: linear insert to unordered arrays, merge sort, iterate into priority list
 *      time complexity: O(n + nlogn + n) = O(nlogn)
 *      space complexity:
 *              mergeSort (sacrifice space for time): O(n)
 *
 *   (for Q2, 2 matrix has to be inserted before mergeSort, time complexity is O(n (both matrix) + nlogn + n) = O(nlogn)
 *
 *   Merge: Merge two arrays into a single array.
 *      time complexity: O(nlogn):
 *          MergeSort: O(nlogn): [Worst case is the full mergeSort]
 *          Insert: O(n) [Re-insert the array as new Node]
 *      space complexity: O(n) : an output array of [leftSize + rightSize]
 *
 *   Search next shortest: Pop(inner stack) or Dequeue(outer priority queue)
 *      time complexity: O(1)
 *
 *   Output: return array when queueSize = 1
 *      runtime: O(1)
 *
 * 10.Efficiency Analysis:
 * (Note that array are interchangeable when they have the same length.)
 * Insertion:
 *      When inserting a new node, there are 3 approaches:
 *      1. Priority queue (as described in 7):
 *          runtime is O(n) or O(i), i is the count of index node in outer queue,  (unique size in arrays).
 *      2. Hashing
 *          runtime is O(n)
 *          search: O(1)
 *          insert: O(n)
 *              Collision Resolving:
 *                  Open address: Degrade into O(n), also memory problem from pigeonhole principle.
 *                                and hashing difficulty of large number of similar data,
 *                                and uneven distribution as size has a heavy bias to low value (2 short array will merge into 1 long array)
 *                  Seperate chaining: similar to the chosen implementation, degrade into O(n).
 *
 *          Maintain pointer to next shortest array: O(n)
 *              (It is because to find the next or previous node,
 *              it must use probing :O(n), or another index structure,
 *              but maintaining the index structure will be O(n), a chicken and egg problem.
 *      3. Access by index on Extendable Array of array size with separate chaining (most time efficient but hard to implement and extremely space inefficient)
 *          Search by index: O(1):
 *          Insert using array size as key : close to O(1) ,see [Extend array below]
 *          Extend array:
 *          O(1), the copy of old data can be done in each insertion instead of right after extend,
 *          (which I had learnt through the helpful solution online,
 *          link: https://stackoverflow.com/questions/4834490/a-data-structure-supporting-o1-random-access-and-worst-case-o1-append
 *          )
 *          However, if we take the characteristic of java into consideration,
 *          the initialization of new array is O(n), [cost of 0-ing out the array]
 *
 *   11. Conclusion:
 *      The priority queue supports O(nlogn) in the worst case, the average time complexity is O(nlogi), i is the count of arrays.
 *
 *      Use flow:
 *      1. Insert by matrix: O(n),
 *      2. Sort by size using mergeSort in Matrix: O(nlogn),
 *      3. Linearly passed into priority queue O(n),
 *      4. MergeSort (in priorityQueue) + reinsert O(nlogn) : O(nlogn + logn * logn)
 *          4.1 Search next shortest Array: O(1),
 *      5. Return last array: O(1)
 *
 * */

/**
 *
 */
public class Q1{
    /**
     * Wrapper method for 3d int array input.
     * @param m
     */
    public static int[] Q1(int[][][] m){
        if (m == null){
            System.out.println("Warning: The matrix provided has a null reference");
            return new int[]{};
        }

        if (m.length == 0){
            System.out.println("Warning: The matrix provided is empty");
            return new int[]{};
        }

        Matrix matrix = new Matrix(m);
        return Q1(matrix);
    }

    /**
     * Wrapper method of 2d int array input.
     * @param m
     */
    public static int[] Q1(int[][] m) {
        if (m == null){
            System.out.println("Warning: The matrix provided has a null reference");
            return new int[]{};
        }

        if (m.length == 0){
            System.out.println("Warning: The matrix provided is empty");
            return new int[]{};
        }
        Matrix matrix = new Matrix(m);
        return Q1(matrix);
    }

    /**
     * A driver method that can be called from Q1Test,
     * connect different classes together, plus presentation through Console Interface
     * @param matrix a unsorted Matrix object prepared in Matrix
     * @return a sorted int[] of all arrays in matrix.
     */
    public static int[] Q1(Matrix matrix){

        System.out.println("\n\n Before sorting by size:");
        matrix.printMatrix();

        matrix.mergeSortBySize();
        System.out.println("\n\n After sorting by size:");
        matrix.printMatrix();

        PriorityQueue priorityQueue = new PriorityQueue(matrix);

        int[] a = priorityQueue.mergeSort();

        System.out.println("\n\n After mergeSort:");
        System.out.println(Arrays.toString(a));

        // output of the algorithm
        return a;



    }

}


