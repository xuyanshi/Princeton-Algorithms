import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
    private Node first = null;
    private int sz = 0;

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current == null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }

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
