public class Percolation {
    boolean[][] open;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        open = new boolean[n][n];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
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
        int N = 8;
        Percolation p = new Percolation(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(p.open[i][j] + " ");
            }
            System.out.println();
        }
    }
}
