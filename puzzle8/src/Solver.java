import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.util.Stack;
>>>>>>> parent of 9273431 (Update Solver.java)

public class Solver {
    private boolean solvable;
    private int minMoves;

    private Node destination;

    private class Node implements Comparable<Node> {

        Board board;
        int moves;
        Node prev;
        int priority;

        public Node(Board board, int moves, Node prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.priority = moves + board.manhattan();
        }


        public int compareTo(Node that) {
            return Integer.compare(priority, that.priority);
        }
    }

    private ArrayList<Board> visitedBoards;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("the argument is null");
        }
        solvable = false;
        minMoves = -1;
        MinPQ<Node> pq = new MinPQ<>();
        visitedBoards = new ArrayList<>();
        pq.insert(new Node(initial, 0, null));
        while (!pq.isEmpty()) {
            Node dequeuedSearchNode = pq.delMin();
            if (dequeuedSearchNode.board.isGoal()) {
                solvable = true;
                minMoves = dequeuedSearchNode.moves;
                destination = dequeuedSearchNode;
                break;
            }
            visitedBoards.add(dequeuedSearchNode.board);
            for (Board bd : dequeuedSearchNode.board.neighbors()) {
<<<<<<< HEAD
                if (visited(bd)) {
                    continue;
=======
                if (dequeuedSearchNode.prev != null && bd.equals(dequeuedSearchNode.prev.board)) {
                    continue; // Not completed
>>>>>>> parent of 9273431 (Update Solver.java)
                }
                pq.insert(new Node(bd, dequeuedSearchNode.moves + 1, dequeuedSearchNode));
            }
        }
    }

    private boolean visited(Board bd) {
        for (int i = visitedBoards.size() - 1; i >= 0; i--) {
            Board visitedBoard = visitedBoards.get(i);
            if (bd.equals(visitedBoard)) {
                return true;
            }
        }
        return false;
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return minMoves;
    }

    // sequence of boards in the shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        Stack<Board> stk = new Stack<>();
        Node node = destination;
        while (node != null) {
            stk.push(node.board);
            node = node.prev;
        }
        return stk;
    }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
