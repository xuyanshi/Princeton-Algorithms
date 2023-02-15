public class Board {
    private int[][] tiles;
    private int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder("" + n);
        for (int i = 0; i < n; i++) {
            sb.append("\n");
            for (int j = 0; j < n - 1; j++) {
                sb.append(tiles[i][j] + " ");
            }
            sb.append(tiles[i][n - 1]);
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int hammingDistance = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int real = tiles[row][col];
                int goal = row * n + col + 1;
                if (real != 0 && real != goal) {
                    hammingDistance++;
                }
            }
        }
        return hammingDistance;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return false;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int n = 3;
        int[][] tiles = {{1, 0, 3}, {4, 2, 5}, {7, 8, 6}};
        Board bd = new Board(tiles);
        System.out.println(bd);
        System.out.println("Hamming: " + bd.hamming());
        System.out.println("Manhattan: " + bd.manhattan());
    }

}
