import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UF {
    private int[] id;

    public UF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        while (id[i] != i) {
            i = id[i];
        }
        return i;
    }

    void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        id[rootP] = rootQ;
    }

    boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    int find(int p) {
        return 0;
    }

    int count() {
        return 0;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf = new UF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}
