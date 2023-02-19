import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private static class Node implements Comparable {

        /* Odd is vertical, even is horizontal.
         * For example, root (level 1) is vertical.
         * */
        private boolean isOdd;
        private Point2D point;
        private Node left, right;

        /* Each node corresponds to an axis-aligned rectangle in the unit square,
         ** which encloses all the points in its subtree. */
        private RectHV rect;

        public Node(boolean isOdd, Point2D point, RectHV rect) {
            this.isOdd = isOdd;
            this.point = point;
            this.rect = rect;
        }


        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    private Node root;
    private int sz;

    // construct an empty set of points
    public KdTree() {
        root = null;
        sz = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // number of points in the set
    public int size() {
        if (root == null) {
            return 0;
        }
        return sz;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("argument is null.");
        }
        if (!contains(p)) {
            // insert
            sz++;
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("argument is null.");
        }
        return false;
    }

    // draw all points to standard draw
    public void draw() {
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
//        for (Point2D p : treeset) {
//            p.draw();
//        }
        StdDraw.show();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("argument is null.");
        }
        ArrayList<Point2D> pts = new ArrayList<>();
//        for (Point2D p : treeset) {
//            if (p.x() <= rect.xmax() && p.x() >= rect.xmin() && p.y() <= rect.ymax() && p.y() >= rect.ymin()) {
//                pts.add(p);
//            }
//        }
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
        double minDistanceSquare = Double.POSITIVE_INFINITY;
        Point2D minDistancePoint = null;
//        for (Point2D point : treeset) {
//            if (p == point) {
//                continue;
//            }
//            if (p.distanceSquaredTo(point) < minDistanceSquare) {
//                minDistanceSquare = p.distanceSquaredTo(point);
//                minDistancePoint = point;
//            }
//        }
        return minDistancePoint;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        KdTree kdtree = new KdTree();
        kdtree.insert(new Point2D(0.7, 0.2));
        System.out.println(kdtree.size());
        System.out.println(kdtree.contains(new Point2D(0.7, 0.2)));

        kdtree.insert(new Point2D(0.5, 0.4));
        System.out.println(kdtree.size());
        System.out.println(kdtree.contains(new Point2D(0.5, 0.4)));

        kdtree.insert(new Point2D(0.2, 0.3));
        kdtree.insert(new Point2D(0.4, 0.7));
        kdtree.insert(new Point2D(0.9, 0.6));
        kdtree.draw();
    }
}
