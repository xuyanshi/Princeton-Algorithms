import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        int idx = 0;
        while (!StdIn.isEmpty()) {
            idx++;
            String s = StdIn.readString();
            if (StdRandom.bernoulli(1.0 / idx)) {
                champion = s;
            }
        }
        StdOut.println(champion);
    }
}
