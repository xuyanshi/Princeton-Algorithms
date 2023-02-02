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

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < sz; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }
}
