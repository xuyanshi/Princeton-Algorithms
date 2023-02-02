import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackOfStrings {
    public StackOfStrings() {

    }

    void push(String item) {

    }

    String pop() {
        return "";
    }

    boolean isEmpty() {
        return true;
    }

    int size() {
        return 0;
    }

    public static void main(String[] args) {
        StackOfStrings stack = new StackOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) {
                StdOut.print(stack.pop());
            } else {
                stack.push(s);
            }
        }
    }
}
