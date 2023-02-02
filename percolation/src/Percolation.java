import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int N;
    WeightedQuickUnionUF grid;
    int top, bottom;
    boolean[] opened;

    int countOfOpenSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        N = n;
        grid = new WeightedQuickUnionUF(n * n + 2); // 0 is virtual top, n*n+1 is virtual bottom
        top = 0;
        bottom = n * n + 1;
        for (int i = 1; i <= n; i++) {
            grid.union(top, i); // virtual top
        }
        for (int i = n * n; i > n * (n - 1); i--) {
            grid.union(bottom, i); // virtual bottom
        }

        opened = new boolean[n * n + 2];
        opened[top] = true;
        opened[bottom] = true;
        countOfOpenSites = 0;
    }

/*
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
*/

    private boolean illegal(int coordinate) {
        return coordinate < 1 || coordinate > N;
    }

    private int flatten(int row, int col) {
        return (row - 1) * N + col;
    }

    private final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (illegal(row) || illegal(col)) {
            throw new IllegalArgumentException();
        }
        opened[flatten(row, col)] = true;
        countOfOpenSites++;
        for (int[] dir : direction) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (!illegal(newRow) && !illegal(newCol) && isOpen(newRow, newCol)) {
                grid.union(flatten(row, col), flatten(newRow, newCol));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (illegal(row) || illegal(col)) {
            throw new IllegalArgumentException();
        }

        return opened[flatten(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (illegal(row) || illegal(col)) {
            throw new IllegalArgumentException();
        }

        return grid.connected(flatten(row, col), top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return countOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(top, bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 2;
        Percolation p = new Percolation(n);
        p.open(1, 1);
        p.open(2, 2);

        System.out.println(p.isOpen(2, 2));

        System.out.println(p.isFull(2, 2));
        System.out.println(p.percolates());
    }
}
