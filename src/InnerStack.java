/**
 * A class of stack implemented by linkedList,
 * support push and popping at front.
 */
public class InnerStack {

    public class SNode {
        int[] array;
        SNode next;

        SNode(int[] a) {
            this.array = a;
            next = null;
        }
    }

    int size;
    SNode head;

    public InnerStack(int[] a) {
        this.size = 1;
        this.head = new SNode(a);
    }

    public void push(int[] a) {
        SNode newNode = new SNode(a);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int[] pop() {
        if (size <= 0) {
            System.out.println("Error: Bucket underflow ");
            return null;
        }
        int[] outArray = head.array;
        head = head.next;
        size--;
        return outArray;
    }



}
