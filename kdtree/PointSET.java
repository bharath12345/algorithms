package kdtree;

import algs4.Point2D;
import stdlib.Draw;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * User: bharadwaj
 * Date: 03/10/13
 * Time: 11:57 AM
 */
public class PointSET {

    TreeSet<Point2D> pointSet = null;

    /**
     * construct an empty set of points
     */
    public PointSET() {
        pointSet = new TreeSet<Point2D>();
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
        List<Point2D> inRange = new ArrayList<Point2D>();
        for(Point2D p: pointSet) {
            if (rect.contains(p)) {
                inRange.add(p);
            }
        }
        return inRange;
    }

    /**
     * a nearest neighbor in the set to p; null if set is empty
     * @param that
     * @return
     */
    public Point2D nearest(Point2D that) {
        Point2D nearest = null;
        double distance = Double.POSITIVE_INFINITY;
        for(Point2D p: pointSet) {
            double newDistance = Math.abs(p.distanceTo(that));
            if (distance > newDistance) {
                distance = newDistance;
                nearest = p;
            }
        }
        return nearest;
    }

    public static void main(String[] args) {
        PointSET pointSET = new PointSET();
        int objCount = Integer.MAX_VALUE/500;
        System.out.println("num of objects = " + objCount);
        for(int i = 0; i < objCount; i++) {
            Point2D point2D = new Point2D(i, i);
            pointSET.insert(point2D);
        }
        Point2D origin = new Point2D(0, 0);
        RectHV rectHV = new RectHV(1, 1, 2, 2);

        long t1 = System.currentTimeMillis();
        System.out.println("nearest start time = " + t1);
        Point2D nearest = pointSET.nearest(origin);
        long t2 = System.currentTimeMillis();
        System.out.println("nearest = " + nearest + " time = " + (t2 - t1));

        Iterable<Point2D> inRange = pointSET.range(rectHV);
        long t3 = System.currentTimeMillis();
        System.out.println("finished points in range. time = " + (t3 - t2));

        int i = 0;
        for(Point2D p: inRange) {
            i++;
        }
        long t4 = System.currentTimeMillis();
        System.out.println("count of points in range = " + i + " time = " + (t4 - t3));
    }
}