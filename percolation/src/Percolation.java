public class Percolation {
    int N;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        N = n;
        UF grid = new UF(n * n + 2); // 0 is virtual top, n*n+1 is virtual bottom

    }

    private class UF {
        private int[] id; // component identifier
        private int[] sz; // size
        int cnt; // count

        public UF(int N) {
            id = new int[N];
            sz = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
                sz[i] = 1;
            }
            cnt = N;
        }

        private int root(int i) {
            while (id[i] != i) {
                id[i] = id[id[i]]; // Path compression
                i = id[i];
            }
            return i;
        }

        void union(int p, int q) {
            int rootP = root(p);
            int rootQ = root(q);
            if (rootP == rootQ) {
                return;
            }
            if (sz[rootP] < sz[rootQ]) {
                id[rootP] = rootQ;
                sz[rootQ] += sz[rootP];
            } else {
                id[rootQ] = rootP;
                sz[rootP] += sz[rootQ];
            }
            cnt--;
        }

        boolean connected(int p, int q) {
            return root(p) == root(q);
        }

        int find(int p) {
            return id[p];
        }

        int count() {
            return cnt;
        }
    }

    private boolean illegal(int coordinate) {
        return coordinate < 1 || coordinate > N;
    }

    private int flatten(int row, int col) {
        return (row - 1) * N + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (illegal(row) || illegal(col)) {
            throw new IllegalArgumentException();
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (illegal(row) || illegal(col)) {
            throw new IllegalArgumentException();
        }

        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (illegal(row) || illegal(col)) {
            throw new IllegalArgumentException();
        }

        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
