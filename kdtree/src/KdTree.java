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

        public Node(boolean isVertical, Point2D point, RectHV rect) {
            this.isVertical = isVertical;
            this.point = point;
            this.rect = rect;
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
            root = insertHelper(root, p, 1);
            sz++;
        }
    }

    private Node insertHelper(Node node, Node parent, Point2D p, boolean isLeft) {
        if (root == null) {
            root = new Node(1, p, new RectHV(0, 0, 1, 1));
            return root;
        }
        if (node == null || p.equals(node.point)) {
            return node;
        }
        if (depth % 2 == 1) { // Odd Level
            if (p.x() < node.point.x()) {
                if (node.left == null) {
                    node.left = new Node(node.depth + 1, p, new RectHV(node.rect.xmin(), node.rect.ymin(), p.x(), node.rect.ymax()));
                } else {
                    insertHelper(node.left, p, node.depth + 1);
                }
            } else {
                if (node.right == null) {
                    node.right = new Node(node.depth + 1, p, new RectHV(p.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax()));
                } else {
                    insertHelper(node.right, p, node.depth + 1);
                }
            }
        } else {
            if (p.y() < node.point.y()) {
                if (node.left == null) {
                    node.left = new Node(node.depth + 1, p, new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), p.y()));
                } else {
                    insertHelper(node.left, p, node.depth + 1);
                }
            } else {
                if (node.right == null) {
                    node.right = new Node(node.depth + 1, p, new RectHV(node.rect.xmin(), p.y(), node.rect.xmax(), node.rect.ymax()));
                } else {
                    insertHelper(node.right, p, node.depth + 1);
                }
            }
        }
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
