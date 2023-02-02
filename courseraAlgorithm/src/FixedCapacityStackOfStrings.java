public class FixedCapacityStackOfStrings {

    private String[] s;
    private int sz = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return sz == 0;
    }

    public void push(String item) {
        s[sz++] = item;
    }

    public String pop() {
        String item = s[--sz];
        s[sz] = null;
        return item;
    }
}
