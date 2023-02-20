import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private static class Node {
        /* Odd is vertical, even is horizontal.
         * For example, root (level 1) is vertical.
         * */
        private boolean isVertical;
        private Point2D point;
        private Node left, right;

        /* Each node corresponds to an axis-aligned rectangle in the unit square,
         ** which encloses all the points in its subtree. */
        private RectHV rect;

        public Node(boolean isVertical, Point2D point, RectHV rect) {
            this.isVertical = isVertical;
            this.point = point;
            this.rect = rect;
        }

        private RectHV leftRect() {
            if (isVertical) {
                return new RectHV(rect.xmin(), rect.ymin(), point.x(), rect.ymax());
            } else {
                return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), point.y());
            }
        }

        private RectHV rightRect() {
            if (isVertical) {
                return new RectHV(point.x(), rect.ymin(), rect.xmax(), rect.ymax());
            } else {
                return new RectHV(rect.xmin(), point.y(), rect.xmax(), rect.ymax());
            }
        }


    }

    private Node root;
    private int sz;

    private double minDistanceSquare;
    private Point2D minDistancePoint;

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
            root = insertHelper(root, null, p, true);
            sz++;
        }
    }

    private Node insertHelper(Node node, Node parent, Point2D p, boolean isLeft) {
        if (node == null) {
            if (parent == null) { // root
                return new Node(true, p, new RectHV(0, 0, 1, 1));
            }
            if (isLeft) {
                return new Node(!parent.isVertical, p, parent.leftRect());
            } else {
                return new Node(!parent.isVertical, p, parent.rightRect());
            }
        }
        if (p.equals(node.point)) {
            return node;
        }
        if (node.isVertical) { // Odd Level
            if (p.x() < node.point.x()) {
                node.left = insertHelper(node.left, node, p, true);
            } else {
                node.right = insertHelper(node.right, node, p, false);
            }
        } else {
            if (p.y() < node.point.y()) {
                node.left = insertHelper(node.left, node, p, true);
            } else {
                node.right = insertHelper(node.right, node, p, false);
            }
        }
        return node;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("argument is null.");
        }
        return containsHelper(root, p, true);
    }

    private boolean containsHelper(Node node, Point2D p, boolean vertical) {
        if (node == null) {
            return false;
        }
        if (p.equals(node.point)) {
            return true;
        }
        if (vertical) {
            if (p.x() < node.point.x()) {
                return containsHelper(node.left, p, false);
            } else {
                return containsHelper(node.right, p, false);
            }
        } else {
            if (p.y() < node.point.y()) {
                return containsHelper(node.left, p, true);
            } else {
                return containsHelper(node.right, p, true);
            }
        }
    }

    // draw all points to standard draw
    public void draw() {
        drawHelper(root, true);
    }

    private void drawHelper(Node node, boolean vertical) {
        if (node == null) {
            return;
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.point.draw();
        StdDraw.setPenRadius();
        if (vertical) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
        }
        if (node.left != null) {
            drawHelper(node.left, !vertical);
        }
        if (node.right != null) {
            drawHelper(node.right, !vertical);
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("argument is null.");
        }
        ArrayList<Point2D> pts = new ArrayList<>();
        rangeHelper(rect, pts, root);
        return pts;
    }

    private void rangeHelper(RectHV rect, ArrayList<Point2D> pts, Node node) {
        if (node == null) {
            return;
        }
        if (rect.contains(node.point)) {
            pts.add(node.point);
        }
        if (node.isVertical) {
            if (node.point.x() > rect.xmax()) {
                rangeHelper(rect, pts, node.left);
            } else if (node.point.x() < rect.xmin()) {
                rangeHelper(rect, pts, node.right);
            } else {
                rangeHelper(rect, pts, node.left);
                rangeHelper(rect, pts, node.right);
            }
        } else {
            if (node.point.y() > rect.ymax()) {
                rangeHelper(rect, pts, node.left);
            } else if (node.point.y() < rect.ymin()) {
                rangeHelper(rect, pts, node.right);
            } else {
                rangeHelper(rect, pts, node.left);
                rangeHelper(rect, pts, node.right);
            }
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("argument is null.");
        }
        if (isEmpty()) {
            return null;
        }
        minDistanceSquare = Double.POSITIVE_INFINITY;
        minDistancePoint = null;
        nearestHelper(p, root);
        return minDistancePoint;
    }

    private void nearestHelper(Point2D p, Node node) {
        if (node == null) {
            return;
        }
        if (p.distanceSquaredTo(node.point) < minDistanceSquare) {
            minDistanceSquare = p.distanceSquaredTo(node.point);
            minDistancePoint = node.point;
        }
        boolean leftVisited = false;
        boolean rightVisited = false;
        if (node.left != null && node.left.rect.contains(p) ||
                (node.left != null && node.right != null && node.left.rect.distanceSquaredTo(p) < node.right.rect.distanceSquaredTo(p))) {
            nearestHelper(p, node.left);
            leftVisited = true;
        }
        if (node.right != null && node.right.rect.contains(p) ||
                (node.left != null && node.right != null && node.left.rect.distanceSquaredTo(p) > node.right.rect.distanceSquaredTo(p))) {
            nearestHelper(p, node.right);
            rightVisited = true;
        }

        if (node.left != null && !leftVisited && minDistanceSquare >= node.left.rect.distanceSquaredTo(p)) {
            nearestHelper(p, node.left);
        }
        if (node.right != null && !rightVisited && minDistanceSquare >= node.right.rect.distanceSquaredTo(p)) {
            nearestHelper(p, node.right);
        }
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
