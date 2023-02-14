public class maxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N;

    public maxPQ(int capacity) {
        pq = (Key[]) new Object[capacity + 1];
        N = 0;

    }

    public maxPQ(Key[] a) {

    }

    void insert(Key v) {

    }

    Key delMax() {
        return null;
    }

    boolean isEmpty() {
        return size() > 0;

    }

    Key max() {
        return null;
    }


    int size() {
        return N;
    }

    private void swim(int k) {

    }

    private void sink(int k) {

    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }


    private void exch(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }
}
