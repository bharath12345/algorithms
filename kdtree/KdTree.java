package kdtree;

import algs4.*;
import stdlib.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: bharadwaj
 * Date: 03/10/13
 * Time: 12:10 PM
 */
public class KdTree {

    // if difference is less than EPSILON then the values are equal
    private final static double EPSILON = 0.0000001;

    class Node {
        private double x;
        private double y;
        private Node left, right;  // left and right subtrees
        private int H;             // height of the subtree
        private RectHV rectHV;

        public Node(double x, double y, int H, RectHV rectHV) {
            this.x = x;
            this.y = y;
            this.H = H;
            this.rectHV = rectHV;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")," + "{h = " + H + "},(rect = " + rectHV + ")";
        }
    }

    class TwoDTree<P extends Point2D> implements Iterable<Point2D> {

        /**
         * root of the 2d-tree
         */
        public Node root = null;

        /**
         * size
         */
        int size = 0;

        public int size() {
            return size;
        }

        private double compare(Node node, P p, int H) {
            if(H % 2 != 0) {
                // if its odd position in tree hierarchy, then, compare x-axis values
                return p.x() - node.x ;
            }

            // if its even position in tree hierarchy, then, compare y-axis values
            return p.y() - node.y;
        }

        private RectHV getRect(Node node, P p, int H, boolean isLeft) {
            if(H % 2 != 0) {
                // odd, vertical split

                if(isLeft) {
                    int xmin = node.x;
                    int ymin = node.y;
                    int xmax = p.x();
                    int ymax =
                } else {

                }
                return new RectHV();
            }

            // even, horizontal split

        }

        private RectHV getRect(int H) {
            if(H == 0) {
                return new RectHV(0, 0, 1, 1);
            }
            return null;
        }

        /***********************************************************************
         *  Search 2d Tree for a given point
         ***********************************************************************/
        public boolean contains(P p) {
            System.out.println("searching node = " + p);
            return get(p) != null;
        }

        private Node get(P p) {
            return get(root, p, 0);
        }

        private Node get(Node node, P p, int H) {
            if (node == null) return null;
            double cmp = compare(node, p, H);
            if (cmp > 0 && cmp < EPSILON) {
                return node;
            }else if (cmp < 0 && cmp > -EPSILON){
                return node;
            } else if (cmp < 0){
                return get(node.left, p, H + 1);
            } else /*if (cmp > 0)*/ {
                return get(node.right, p, H + 1);
            }
        }

        /***********************************************************************
         *  Insert the point
         ***********************************************************************/
        public boolean add(P p) {
            if(!contains(p)) {
                size++;
            } else {
                System.out.println("node = " + p + " already in the tree... not adding it again");
                return false;
            }
            root = add(root, p, 0);
            return true;
        }

        private Node add(Node node, P p, int H) {
            if (node == null) {
                System.out.println("adding new node = " + p);
                return new Node(p.x(), p.y(), H, getRect(H));
            }

            H = H + 1;
            double cmp = compare(node, p, H);
            System.out.println("compare value = " + cmp + " H = " + H);

            if (cmp > 0 && cmp < EPSILON) {
                System.out.println("not adding almost equal node = " + p + " cmp = " + cmp);
            } else if (cmp < 0 && cmp > -EPSILON) {
                System.out.println("not adding almost equal node = " + p + " cmp = " + cmp);
            } else if (cmp < 0) {
                Node leftNode = add(node.left, p, H);
                leftNode.rectHV = getRect(node, p, H, true);
                if(node.left == null) {
                    node.left = leftNode;
                    System.out.println("adding node = " + p + " to the LEFT of = " + node);
                } else {
                    System.out.println("moving node = " + p + " to the LEFT of = " + node);
                }
            } else if (cmp > 0) {
                Node rightNode = add(node.right, p, H);
                rightNode.rectHV = getRect(node, p, H, false);
                if(node.right == null) {
                    node.right = rightNode;
                    System.out.println("adding node = " + p + " to the RIGHT of = " + node);
                } else {
                    System.out.println("moving node = " + p + " to the RIGHT of = " + node);
                }
            }
            return node;
        }

        @Override
        public Iterator<Point2D> iterator() {
            Queue<Point2D> points = new Queue<Point2D>();
            Queue<Node> queue = new Queue<Node>();
            queue.enqueue(root);
            while (!queue.isEmpty()) {
                Node node = queue.dequeue();
                if (node == null) continue;
                points.enqueue(new Point2D(node.x, node.y));
                queue.enqueue(node.left);
                queue.enqueue(node.right);
            }
            return points.iterator();
        }
    }

    TwoDTree<Point2D> pointSet = null;

    /**
     * construct an empty set of points
     */
    public KdTree() {
        pointSet = new TwoDTree<Point2D>();
    }

    /**
     * is the set empty?
     * @return
     */
    public boolean isEmpty() {
        if (pointSet.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * number of points in the set
     * @return
     */
    public int size() {
        return pointSet.size();
    }

    /**
     * add the point p to the set (if it is not already in the set)
     * @param p
     */
    public void insert(Point2D p) {
        System.out.println("\n\nadding node = " + p);
        pointSet.add(p);
    }

    /**
     * does the set contain the point p?
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {
        if (pointSet.contains(p)) {
            return true;
        }
        return false;
    }

    /**
     * draw all of the points to standard draw
     */
    public void draw() {
        Draw draw = new Draw();
        for(Point2D p: pointSet) {
            draw.point(p.x(), p.y());
        }
    }

    /**
     * all points in the set that are inside the rectangle
     * @param rectHV
     * @return
     */
    public Iterable<Point2D> range(RectHV rectHV) {
        List<Point2D> inRange = new ArrayList<Point2D>();
        search(inRange, pointSet.root, rectHV);
        return inRange;
    }

    private void search(List<Point2D> inRange, Node node, RectHV rectHV) {
        if(node == null || rectHV == null || inRange == null) return;

        double xmax = node.x, ymax = node.y;
        RectHV rect = new RectHV(xmin, ymin, xmax, ymax);
        if(rectHV.intersects(rect)) {
            search(inRange, node.left, rectHV);
            search(inRange, node.right, rectHV);

            Point2D point = new Point2D(node.x, node.y);
            if(rectHV.contains(point)) {
                inRange.add(point);
            }
        }
    }

    /**
     * a nearest neighbor in the set to p; null if set is empty
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        //ToDo: Use 2d-tree algorithm per the specification
        return null;
    }

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        double [][] obj = {{0.7, 0.1}, {0.5, 0.4}, {0.2, 0.3}, {0.4, 0.7}, {0.9, 0.6}, {0.8, 0.5}};
        System.out.println("num of objects = " + obj.length);
        for(int i = 0; i < obj.length; i++) {
            Point2D point2D = new Point2D(obj[i][0], obj[i][1]);
            kdTree.insert(point2D);
        }
        Point2D origin = new Point2D(0, 0);
        RectHV rectHV = new RectHV(0.4, 0.4, 0.6, 0.6);

        long t1 = System.currentTimeMillis();
        System.out.println("nearest start time = " + t1);
        Point2D nearest = kdTree.nearest(origin);
        long t2 = System.currentTimeMillis();
        System.out.println("nearest = " + nearest + " time = " + (t2 - t1));

        Iterable<Point2D> inRange = kdTree.range(rectHV);
        long t3 = System.currentTimeMillis();
        System.out.println("finished points in range. time = " + (t3 - t2));

        int i = 0;
        for(Point2D p: inRange) {
            i++;
        }
        long t4 = System.currentTimeMillis();
        System.out.println("count of points in range = " + i + " time = " + (t4 - t3));

        System.out.println("******* Level order traversal *******");
        for(Point2D p: kdTree.pointSet) {
            System.out.println("point = " + p);
        }
    }

}
