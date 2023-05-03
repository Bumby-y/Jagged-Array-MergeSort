import java.util.Arrays;    //It is only used in visualization for demonstration and debugging and can be completely removed.


/**
 * An outer Index queue implemented by linkedlist,
 * Priority Node (PNode)
 *      1. key (array size)
 *      2. bucket : pointer to innerStack
 *      3. next: pointer to next PNode.
 *
 * supports:
 *      O(n) array insertion
 *      O(n) Sorted Matrix insertion( Must be performed on a new PriorityQueue )
 *      O(1) output shortest array,
 */
public class PriorityQueue {

    private class PNode {
        int key;
        InnerStack bucket;
        PNode next;

        PNode() {
            key = 0;
            next = null;
        }

        /**
         * Creating a new PNode if the array has a unique array size
         * and the corresponding InnerStack and Stack Node (SNode)
         *
         * @param a int[]
         */
        PNode(int[] a) {
            this.key = a.length;
            bucket = new InnerStack(a);
        }
    }
    // A size variable of the priority queue
    public int Psize;
    // head pointer to a head sentinel node
    public PNode headSentinel;
    // misc variable only for demonstration
    static int mergeIteration = 1;

    PriorityQueue() {
        headSentinel = new PNode();
        Psize = 0;
    }

    /**
     * Constructor taking a sorted matrix as argument, linearly parsing the matrix into PriorityQueue
     * @param m a sorted matrix prepared in Matrix
     */
    PriorityQueue(Matrix m) {
        this();
        PNode cur = headSentinel;
        // iterate through int[i] in int[][]
        for (int[] a : m.matrix) {
            int key = a.length;
            if (key < cur.key) {
                System.out.println("Error: Unsorted matrix detected in PriorityQueue(Matrix)");
                return;
            }
            // when the current array size matches the PNode key
            if (key == cur.key) {
                cur.bucket.push(a);
                continue;
            }
            // when the current array size exceed the PNode key, insert a new node at tail.
            // note that cur.next should be null during initialization, unless a new node is being created
            if (key > cur.key) {
                PNode temp = cur.next;                          //redundant but safer.
                cur.next = new PNode(a);
                cur.next.next = temp;
                cur = cur.next;
                Psize++;
            }
        }
    }

    /**
     * A insert method with linear traversal, and should only be used to reinsert output array from merging.
     * @param a int[]
     */
    public void insert(int[] a) {
        // if head is empty
        if (headSentinel.next == null) {
            headSentinel.next = new PNode(a);
            Psize++;
        }
        else {
            PNode cur = headSentinel;
            int aKey = a.length;
            while (aKey > cur.key && cur.next != null) {
                // if cur.key < aKey < cur.next.key, insert a new node after cur
                if (aKey < cur.next.key) {
                    PNode temp = cur.next;
                    cur.next = new PNode(a);
                    cur.next.next = temp;
                    Psize++;
                    return;
                }
                cur = cur.next;
            }
            if (aKey == cur.key) {
                cur.bucket.push(a);
                return;
            }
            // if aKey > tail.key
            if (cur.next == null) {
                cur.next = new PNode(a);
                Psize++;
                return;
            }
            //debug message
            System.out.println("Error: default case reached in insert.");
        }
    }

    /**
     * A method that retrieves the next shortest array, headSentinel.next(PNode).bucket(InnerStack).head(SNode).array,
     * pops from SNode, and when the InnerStack is empty, dequeues the PNode.
     * @return int[] shortest array
     */
    public int[] getNextArray() {
        if (Psize <= 0) {
            System.out.println("Error: Sentinel Node reached by getNextArray");
            return null;
        } else if (Psize < 0 ) {
            System.out.println("Error: under flow in getNextArray");
            return null;
        }
        int[] outArray = headSentinel.next.bucket.pop();
        if (headSentinel.next.bucket.isEmpty()) dequeue();
        return outArray;
    }

    /**
     * When the PNode of the lowest key is empty, remove it from Priority queue.
     */
    private void dequeue() {
        if (headSentinel.next == null) {
            System.out.println("Error: Underflow in dequeue");
        }
        headSentinel.next.bucket = null;
        PNode temp = headSentinel.next.next;
        headSentinel.next.next = null;
        headSentinel.next = temp;
        Psize--;
    }

    /**
     * Check if the Priority queue already has the sorted Array from MergeSort, when array count == 1
     * @return
     */
    public boolean isLast() {
        if (Psize > 1) return false;
        if (Psize == 1 && headSentinel.next.bucket.size == 1) {
            return true;
        }
        return false;
    }

    /**
     * A linked list approach to mergeSort, dequeue to get the shortest array, the result array is reinserted into the linkedlist.
     * @return int[] of the result array.
     */
    public int[] mergeSort() {
        PNode cur =headSentinel.next;

        //Debug function, shows the PNode list and the respective SNode list
        System.out.println("\n\n\nVisualization of priority queue");
        while (cur != null){
            System.out.println("Index node: "+ cur.key + " points to:");
            InnerStack inner = cur.bucket;
            InnerStack.SNode innerCur = inner.head;
            while (innerCur != null){
                System.out.print(Arrays.toString(innerCur.array)+ " -> ");
                innerCur = innerCur.next;
            }
            System.out.println("null");
            cur = cur.next;
        }
        System.out.println("\n");

        if (Psize == 0){
            System.out.println("Error: mergeSort performed on a empty matrix");
        }
        //Repeatingly merge the arrays, until there's only 1 left
        while(!isLast()){
            merge();
        }
        mergeIteration = 1;
        return headSentinel.next.bucket.pop();
    }

    /**
     * A mostly standard merge method, with a twist of initializing left and right array, through dequeue.
     */
    private void merge() {
        int[] left = getNextArray();
        int[] right = getNextArray();
        int leftSize = left.length;
        int rightSize = right.length;
        int[] a = new int[leftSize+rightSize];

        int leftInd, rightInd, i;
        leftInd = rightInd = 0;
        i = 0;

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

        //Debug function, shows merge iteration, arrays pre-sorted and post-sorted.
        System.out.println("Merge iteration: " + mergeIteration++);
        System.out.println("leftArray: " + Arrays.toString(left));
        System.out.println("rightArray: " + Arrays.toString(right));
        System.out.println("SortedArray: " + Arrays.toString(a) + "\n");
        insert(a);
    }

}
