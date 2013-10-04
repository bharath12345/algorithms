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

        return inRange;
    }

    /**
     * a nearest neighbor in the set to p; null if set is empty
     * @param that
     * @return
     */
    public Point2D nearest(Point2D that) {
        TreeSet<Point2D> pointSetDistance = new TreeSet<Point2D>(that.DISTANCE_TO_ORDER);
        pointSetDistance.addAll(pointSet);

        System.out.println("nearest = " + pointSetDistance.first() + " farthest = " + pointSetDistance.last());
        return pointSetDistance.first();

        /*Point2D nearest = null;
        double distance = 0;
        for(Point2D p: pointSet) {
            double newDistance = p.distanceTo(that);
            if (distance > newDistance) {
                distance = newDistance;
                nearest = p;
            }
        }
        return nearest;*/
    }
}
