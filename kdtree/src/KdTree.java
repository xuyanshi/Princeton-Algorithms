import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.TreeSet;

public class KdTree {
    private TreeSet<Point2D> treeset;

    // construct an empty set of points
    public KdTree() {
        treeset = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return treeset.isEmpty();
    }

    // number of points in the set
    public int size() {
        return treeset.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("argument is null.");
        }
        if (!contains(p)) {
            treeset.add(p);
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("argument is null.");
        }
        return treeset.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        for (Point2D p : treeset) {
            p.draw();
        }
        StdDraw.show();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("argument is null.");
        }
        ArrayList<Point2D> pts = new ArrayList<>();
        for (Point2D p : treeset) {
            if (p.x() <= rect.xmax() && p.x() >= rect.xmin() && p.y() <= rect.ymax() && p.y() >= rect.ymin()) {
                pts.add(p);
            }
        }
        return pts;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("argument is null.");
        }
        if (isEmpty()) {
            return null;
        }
        double minDistanceSquare = Double.MAX_VALUE;
        Point2D minDistancePoint = null;
        for (Point2D point : treeset) {
            if (p == point) {
                continue;
            }
            if (p.distanceSquaredTo(point) < minDistanceSquare) {
                minDistanceSquare = p.distanceSquaredTo(point);
                minDistancePoint = point;
            }
        }
        return minDistancePoint;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
