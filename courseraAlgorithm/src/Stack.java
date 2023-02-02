public class Stack<T> {
    private Node first = null;
    private int sz = 0;

    private class Node {
        T item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        sz++;
    }

    public T pop() {
        T item = null;
        if (sz > 0) {
            item = first.item;
            first = first.next;
            sz--;
        }
        return item;
    }
}
