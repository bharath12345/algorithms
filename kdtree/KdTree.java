package kdtree;

import algs4.*;
import stdlib.*;

import java.util.Iterator;

/**
 * User: bharadwaj
 * Date: 03/10/13
 * Time: 12:10 PM
 */
public class KdTree {

    // if difference is less than EPSILON then the values are equal
    private final static double EPSILON = 0.0000001;

    class TwoDTree<P extends Point2D> implements Iterable<Point2D> {

        private class Node {
            private double x;
            private double y;
            private Node left, right;  // left and right subtrees
            private int H;             // height of the subtree

            public Node(double x, double y, int H) {
                this.x = x;
                this.y = y;
                this.H = H;
            }
        }

        /**
         * root of the 2d-tree
         */
        private Node root = null;

        /**
         * size
         */
        int size = 0;

        public int size() {
            return size;
        }

        private double compare(Node node, P p) {
            if(node.H % 2 != 0) {
                // if its odd position in tree hierarchy, then, compare x-axis values
                return p.x() - node.x;
            }

            // if its even position in tree hierarchy, then, compare y-axis values
            return  p.y() - node.y;
        }

        /***********************************************************************
         *  Search 2d Tree for a given point
         ***********************************************************************/
        public boolean contains(P p) {
            return get(p) != null;
        }

        private Node get(P p) {
            return get(root, p);
        }

        private Node get(Node node, P p) {
            if (node == null) return null;
            double cmp = compare(node, p);
            if      (cmp > 0 && cmp < EPSILON)      return node;
            if      (cmp < 0 && cmp > -EPSILON)     return node;
            else if (cmp < 0)                       return get(node.left, p);
            else /*if (cmp > 0)*/                   return get(node.right, p);
        }

        /***********************************************************************
         *  Insert the point
         ***********************************************************************/
        public boolean add(P p) {
            if(!contains(p)) {
                size++;
            }
            root = add(root, p, 0);
            return true;
        }

        private Node add(Node node, P p, int H) {
            if (node == null) return new Node(p.x(), p.y(), H);
            double cmp = compare(node, p);
            if      (cmp > 0 && cmp < EPSILON)      { }
            else if (cmp < 0 && cmp > -EPSILON)     { }
            else if (cmp < 0)                       node.left  = add(node.left, p, node.H + 1);
            else if (cmp > 0)                       node.right = add(node.right, p, node.H + 1);
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
        if (!pointSet.contains(p)) {
            pointSet.add(p);
        }
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
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        //ToDo: Use 2d-tree algorithm per the specification
        return null;
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

}
