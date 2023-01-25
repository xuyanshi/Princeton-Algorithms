import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

public class RandomWordWithoutAlgs4 {
    public static void main(String[] args) {
        String champion = "";
        int idx = 0;
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            idx++;
            String s = input.next();
            if(StdRandom.bernoulli(1.0/idx)) {
                champion = s;
            }
        }
        StdOut.println(champion);
    }
}
