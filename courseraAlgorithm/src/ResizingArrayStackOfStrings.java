public class ResizingArrayStackOfStrings {
    private String[] s;
    private int sz = 0;

    public ResizingArrayStackOfStrings() {
        s = new String[1];
    }

    public void push(String item) {
        if (sz == s.length) {
            resize(2 * s.length);
        }
    }

    public String pop() {
        String item = s[--sz];
        s[sz] = null;
        if (sz > 0 && sz <= s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < sz; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }
}
