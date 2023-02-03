import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
        Node prior;

        public Node() {
            this.item = null;
            this.next = null;
            this.prior = null;
        }

        public Node(Item item) {
            this.item = item;
            this.next = null;
            this.prior = null;
        }
    }

    private int sz = 0;
    private Node first, last;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return sz == 0;
    }

    // return the number of items on the deque
    public int size() {
        return sz;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("the client calls either addFirst() or addLast() with a null argument.");
        }
        Node node = new Node(item);
        if (isEmpty()) {
            last = node;
        } else {
            node.next = first;
            first.prior = node;
        }
        first = node;
        sz++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("the client calls either addFirst() or addLast() with a null argument.");
        }
        Node node = new Node(item);
        if (isEmpty()) {
            first = node;
        } else {
            last.next = node;
            node.prior = last;
        }
        last = node;
        sz++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("the client calls either removeFirst() or removeLast when the deque is empty.");
        }
        Item item = first.item;
        if (sz == 1) {
            first = null;
            last = null;
        } else {
            Node newFirst = first.next;
            first.next = null;
            first = newFirst;
        }
        sz--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("the client calls either removeFirst() or removeLast() when the deque is empty.");
        }
        Item item = last.item;
        if (sz == 1) {
            first = null;
            last = null;
        } else {
            Node newLast = last.prior;
            last.prior = null;
            last = newLast;
        }
        sz--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator<>();
    }

    private class DequeIterator<Item> implements Iterator<Item> {

        private Node current = new Node();

        private DequeIterator() {
            current.next = first;
        }

        @Override
        public boolean hasNext() {
            return current != null && current.next != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("the client calls the next() method in the iterator when there are no more items to return.");
            }
            current = current.next;
            return (Item) current.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("the client calls the remove() method in the iterator.");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(5);

        Iterator<Integer> it = deque.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        deque.removeFirst();

        Iterator<Integer> it2 = deque.iterator();
        while (it2.hasNext()) {
            System.out.print(it2.next() + " ");
        }
    }

}