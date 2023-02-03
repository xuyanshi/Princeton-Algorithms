import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int capacity = 8;

    private int sz = 0;

    private Item[] qu;

    // construct an empty randomized queue
    public RandomizedQueue() {
        qu = (Item[]) new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return sz == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return sz;
    }

    private void resize() {
        if (sz > capacity - 2 || 4 * sz > 3 * capacity) {
            capacity *= 2;
            Item[] newQu = (Item[]) new Object[capacity];
            for (int i = 0; i < sz; i++) {
                newQu[i] = qu[i];
            }
            qu = newQu;
        }

    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("the client calls enqueue() with a null argument.");
        }
        qu[sz++] = item;
        resize();
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("the client calls either sample() or dequeue() when the randomized queue is empty.");
        }
        int idx = StdRandom.uniformInt(sz);
        Item item = qu[idx];
        qu[idx] = qu[sz - 1];
        sz--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("the client calls either sample() or dequeue() when the randomized queue is empty.");
        }
        int idx = StdRandom.uniformInt(sz);
        return qu[idx];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<>();
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {
        Item[] newQu;
        int idx;

        public RandomizedQueueIterator() {
            newQu = (Item[]) new Object[capacity];
            for (int i = 0; i < sz; i++) {
                newQu[i] = (Item) qu[i];
            }
            StdRandom.shuffle(newQu, 0, sz);
            idx = 0;
        }

        @Override
        public boolean hasNext() {
            return idx < sz;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("the client calls the next() method in the iterator when there are no more items to return.");
            }

            return newQu[idx++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("the client calls the remove() method in the iterator.");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.dequeue();

        Iterator<Integer> it = rq.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }

}
