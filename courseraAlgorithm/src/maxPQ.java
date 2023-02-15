public class maxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N;

    public maxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
        N = 0;
    }

    public maxPQ(Key[] a) {
        pq = (Key[]) new Comparable[2 * a.length + 1];
        for (Key v : a) {
            insert(v);
        }
        N = a.length;
    }

    void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    Key delMax() {
        Key maxItem = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = null;
        return maxItem;
    }

    boolean isEmpty() {
        return size() > 0;

    }

    Key max() {
        if (!isEmpty()) {
            return pq[1];
        }
        return null;
    }


    int size() {
        return N;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k < N) {
            int j = 2 * k;
            if (less(2 * j, 2 * j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
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
