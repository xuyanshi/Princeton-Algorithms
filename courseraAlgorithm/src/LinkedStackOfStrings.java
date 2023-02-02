import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedStackOfStrings {
    private class Node {
        String item;
        Node next;
    }

    private Node first = null;
    private int sz = 0;

    public LinkedStackOfStrings() {

    }

    void push(String item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        sz++;
    }

    String pop() {
        if (sz > 0) {
            String item = first.item;
            first = first.next;
            sz--;
            return item;
        }
        return null;
    }

    boolean isEmpty() {
        return first == null;
    }

    int size() {
        return sz;
    }

    public static void main(String[] args) {
        LinkedStackOfStrings stack = new LinkedStackOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) {
                StdOut.print(stack.pop());
            } else {
                stack.push(s);
            }
        }
    }
}
