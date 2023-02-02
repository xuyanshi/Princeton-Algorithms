public class FixedCapacityStack<T> {
    private T[] s;
    private int sz = 0;

    public FixedCapacityStack(int capacity) {
        s = (T[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return sz == 0;
    }

    public void push(T item) {
        s[sz++] = item;
    }

    public T pop() {
        T item = s[--sz];
        s[sz] = null;
        return item;
    }
}
