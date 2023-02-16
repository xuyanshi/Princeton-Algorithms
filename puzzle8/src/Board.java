import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Board {
    private int[][] tiles;
    private int n;

    private int zeroRow;
    private int zeroCol;

    private int hammingDistance;
    private int manhattanDistance;
    private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
                if (tiles[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }

        hammingDistance = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int real = tiles[row][col];
                int goal = row * n + col + 1;
                if (real != 0 && real != goal) {
                    hammingDistance++;
                }
            }
        }

        manhattanDistance = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int real = tiles[row][col];
                if (real != 0) {
                    int supposedRow = (real - 1) / n;
                    int supposedCol = (real - 1) % n;
                    manhattanDistance += Math.abs(row - supposedRow);
                    manhattanDistance += Math.abs(col - supposedCol);
                }
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
        return hammingDistance;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattanDistance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hammingDistance == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || getClass() != y.getClass()) {
            return false;
        }

        Board board = (Board) y;

        if (n != board.n) {
            return false;
        }

        return Arrays.deepEquals(tiles, board.tiles);
    }


    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return new BoardIterator<>();
            }
        };
    }


    private class BoardIterator<T> implements Iterator<T> {
        int leftDirections;
        ArrayList<ArrayList<Integer>> legalLocations;

        public BoardIterator() {
            leftDirections = 0;
            legalLocations = new ArrayList<>();
            for (int[] dir : directions) {
                int x = zeroRow + dir[0], y = zeroCol + dir[1];
                if (legal(x, y)) {
                    leftDirections++;
                    legalLocations.add(new ArrayList<>());
                    legalLocations.get(legalLocations.size() - 1).add(x);
                    legalLocations.get(legalLocations.size() - 1).add(y);
                }
            }
        }

        @Override
        public boolean hasNext() {
            return leftDirections > 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next item.");
            }
            ArrayList<Integer> location = legalLocations.get(legalLocations.size() - leftDirections);
            int x = location.get(0), y = location.get(1);
            leftDirections--;
            int[][] neighborTiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    neighborTiles[i][j] = tiles[i][j];
                }
            }
            neighborTiles[zeroRow][zeroCol] = tiles[x][y];
            neighborTiles[x][y] = 0;

            return (T) new Board(neighborTiles);
        }
    }


    private boolean legal(int row, int col) {
        if (row < 0 || row >= n) {
            return false;
        }
        if (col < 0 || col >= n) {
            return false;
        }
        return true;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int x1 = StdRandom.uniformInt(n);
        int y1 = StdRandom.uniformInt(n);
        while (tiles[x1][y1] == 0) {
            x1 = StdRandom.uniformInt(n);
            y1 = StdRandom.uniformInt(n);
        }
        int x2 = StdRandom.uniformInt(n);
        int y2 = StdRandom.uniformInt(n);
        while (tiles[x2][y2] == 0 || flatten(x1, y1) == flatten(x2, y2)) {
            x2 = StdRandom.uniformInt(n);
            y2 = StdRandom.uniformInt(n);
        }
        int[][] twinTiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                twinTiles[i][j] = tiles[i][j];
            }
        }
        int tmp = twinTiles[x1][y1];
        twinTiles[x1][y1] = twinTiles[x2][y2];
        twinTiles[x2][y2] = tmp;
        return new Board(twinTiles);
    }

    private int flatten(int x, int y) {
        return x * n + y;
    }

    // unit testing (not graded)
    public static void main(String[] args) {

        int[][] tiles = {{1, 0, 3}, {4, 2, 5}, {7, 8, 6}};
        Board bd = new Board(tiles);
        System.out.println(bd);
        System.out.println("Hamming: " + bd.hamming());
        System.out.println("Manhattan: " + bd.manhattan());
        System.out.println("isGoal: " + bd.isGoal());

        Board bd1 = new Board(tiles);
        System.out.println("Equal: " + bd.equals(bd1));

        System.out.println("Neighbors: ");
        for (Board board : bd.neighbors()) {
            System.out.println(board);
        }

        System.out.println("Twin: ");
        System.out.println(bd.twin());

    }

}
