import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private List<LineSegment> lsList;

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        lsList = new ArrayList<>();
        if (points == null) {
            throw new IllegalArgumentException("the argument to the constructor is null");
        }
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("point in the array is null");
            }
        }
        int n = points.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("the argument to the constructor contains a repeated point");
                }
            }
        }
        if (n < 4) {
            return;
        }
        Point[] tmp = new Point[n];
        System.arraycopy(points, 0, tmp, 0, n);
        Arrays.sort(tmp);

        for (Point p : tmp) {
            Arrays.sort(tmp, p.slopeOrder());
            Point start = p;
            Point end = p;
            for (int i = 0; i < n; ) {
                if (p.compareTo(tmp[i++]) == 0) { // tmp[i] is p.
                    continue;
                }

                if (i + 3 > n) {
                    break;
                }
                double k = p.slopeTo(tmp[i]);
                if (p.slopeTo(tmp[i + 3]) == k) {
                    int j = i + 4;
                    for (; j < n; j++) {
                        if (p.slopeTo(tmp[j]) != k) {
                            break;
                        }
                    }
                    start = tmp[i];
                    end = tmp[j - 1];
                    lsList.add(new LineSegment(start, end));
                    i = j;
                } else {
                    i++;
                }
            }
        }

    }

    // the number of line segments
    public int numberOfSegments() {
        if (lsList == null) {
            return 0;
        }
        return lsList.size();
    }

    // the line segments
    public LineSegment[] segments() {
        if (lsList != null) {
            LineSegment[] ls = new LineSegment[numberOfSegments()];
            ls = lsList.toArray(ls);
            return ls;
        }
        return new LineSegment[0];
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
